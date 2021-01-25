package com.arrow.paymanagegrow;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flags);
        }
        setContentView(R.layout.onboarding);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        final LinearLayout l1 = findViewById(R.id.onboarding_l_1);
        final LinearLayout l2 = findViewById(R.id.onboarding_l_2);
        AnimationTranslation(l2, "translationY", 1500 , 0);
        final Button button = findViewById(R.id.get_started_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AnimationTranslation(l1, "translationY", 1500 , 500);
                AnimationTranslation(l2, "translationY", 0 , 500, 500);
            }
        });

        // mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher("KR")); // API Level 21. Country Code

    }


    //###################{ Анимации }###################//

    /** Изменение прозрачности объекта в анимации
     * @param iv - объект View
     * @param duration - время анимации
     * @param setStartOffset - задержка анимации
     * @param setFillAfter - булевое значение
     * @param alphafrom - начальная прозрачность
     * @param alphato - оконечная прозрачность
     * */
    public void SetAlpha(View iv, int duration, int setStartOffset, boolean setFillAfter, float alphafrom, float alphato){
        AlphaAnimation animation1 = new AlphaAnimation(alphafrom, alphato);
        animation1.setDuration(duration);
        animation1.setStartOffset(setStartOffset);
        animation1.setFillAfter(true);
        iv.startAnimation(animation1);
    }

    /** Анимация изменения цвета у текста
     * @param textView - объект TextView
     * @param colorFrom - начальнй цвет текста
     * @param colorTo - Конечный цвет текста
     * */
    public void AnimationColor(final TextView textView, Integer colorFrom, Integer colorTo){
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setTextColor((Integer)animator.getAnimatedValue());
            }
        });
    }

    /** Анимация перемещения объекта
     * @param view - объект View
     * @param property - свойство перемещения
     * @param leght - Длина перемещения
     * @param duration - Время перемещения
     * */
    public void AnimationTranslation(View view, String property, float leght, int duration){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, property, leght);
        animation.setDuration(duration);
        animation.start();
    }

    /** Анимация перемещения объекта с задержкой
     * @param view - объект View
     * @param property - свойство перемещения
     * @param leght - Длина перемещения
     * @param duration - Время перемещения
     * @param StartOffset - задержка
     * */
    public void AnimationTranslation(final View view, final String property, final float leght, final int duration, int StartOffset){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation = ObjectAnimator.ofFloat(view, property, leght);
                animation.setDuration(duration);
                animation.start();
            }
        }, StartOffset);

    }
}