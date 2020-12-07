package kr.co.ddophi.calculaterforkids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val plusOperator = 10
    private val minusOperator = 11
    private val multiplyOperator = 12
    private val divideOperator = 13

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideKeyboard()
        customKeyboard()
    }

    //안드로이드 키보드 숨기기
    private fun hideKeyboard(){
        firstNumber.setTextIsSelectable(true)
        firstNumber.showSoftInputOnFocus = false
        secondNumber.setTextIsSelectable(true)
        secondNumber.showSoftInputOnFocus = false
        operator.setTextIsSelectable(true)
        operator.showSoftInputOnFocus = false
    }

    //안드로이드 키보드 대신 직접 만든 키보드
    private fun customKeyboard() {
        firstNumber.setOnFocusChangeListener { v, hasFocus ->
            customKeyboardNumber(firstNumber, hasFocus)
        }
        secondNumber.setOnFocusChangeListener { v, hasFocus ->
            customKeyboardNumber(secondNumber, hasFocus)
        }
        operator.setOnFocusChangeListener { v, hasFocus ->
            customKeyboardOperator(operator, hasFocus)
        }
        btnResult.setOnClickListener {
            showResult()
        }
    }

    //계산 결과 보여주기
    private fun showResult(){
        val firstNum = firstNumber.text.toString().toInt()
        val secondNum = secondNumber.text.toString().toInt()
        val op = operator.text.toString()
        var result : Int? = null

        when(op){
            "＋" -> {
                result = firstNum + secondNum
            }
            "－" -> {
                result = firstNum - secondNum
            }
            "×" -> {
                result = firstNum * secondNum
            }
            "÷" -> {
                result = firstNum / secondNum
            }
        }

        if(result == null){

        }else
            txtResult.text = "=   ${result}"
    }

    //숫자 입력했을 떄의 동작
    private fun customKeyboardNumber(view: EditText, hasFocus :Boolean){
        btnOne.setOnClickListener {
            if(hasFocus) {
                view.setText("1")
                moveFocus(view)
                imgAppearAnim(view, 1)
            }
        }
        btnTwo.setOnClickListener {
            if(hasFocus) {
                view.setText("2")
                moveFocus(view)
                imgAppearAnim(view, 2)
            }
        }
        btnThree.setOnClickListener {
            if(hasFocus) {
                view.setText("3")
                moveFocus(view)
                imgAppearAnim(view, 3)
            }
        }
        btnFour.setOnClickListener {
            if(hasFocus) {
                view.setText("4")
                moveFocus(view)
                imgAppearAnim(view, 4)
            }
        }
        btnFive.setOnClickListener {
            if(hasFocus) {
                view.setText("5")
                moveFocus(view)
                imgAppearAnim(view, 5)
            }
        }
        btnSix.setOnClickListener {
            if(hasFocus) {
                view.setText("6")
                moveFocus(view)
                imgAppearAnim(view, 6)
            }
        }
        btnSeven.setOnClickListener {
            if(hasFocus) {
                view.setText("7")
                moveFocus(view)
                imgAppearAnim(view, 7)
            }
        }
        btnEight.setOnClickListener {
            if(hasFocus) {
                view.setText("8")
                moveFocus(view)
                imgAppearAnim(view, 8)
            }
        }
        btnNine.setOnClickListener {
            if(hasFocus) {
                view.setText("9")
                moveFocus(view)
                imgAppearAnim(view, 9)
            }
        }
        btnZero.setOnClickListener {
            if(hasFocus) {
                view.setText("0")
                moveFocus(view)
                imgAppearAnim(view, 0)
            }
        }
    }

    //연산자 입력했을 떄의 동작
    private fun customKeyboardOperator (view: EditText, hasFocus: Boolean){
        btnPlus.setOnClickListener {
            if(hasFocus) {
                view.setText("＋")
                moveFocus(view)
                imgAppearAnim(view, plusOperator)
            }
        }
        btnMinus.setOnClickListener {
            if(hasFocus) {
                view.setText("－")
                moveFocus(view)
                imgAppearAnim(view, minusOperator)
            }
        }
        btnMultiply.setOnClickListener {
            if(hasFocus) {
                view.setText("×")
                moveFocus(view)
                imgAppearAnim(view, multiplyOperator)
            }
        }
        btnDivide.setOnClickListener {
            if(hasFocus) {
                view.setText("÷")
                moveFocus(view)
                imgAppearAnim(view, divideOperator)
            }
        }
    }

    //Edit text 에 입력하고 나면 다음걸로 포커스 넘어가기
    private fun moveFocus (view : EditText){
        when(view){
            firstNumber -> {
                operator.requestFocus()
            }
            operator -> {
                secondNumber.requestFocus()
            }
            secondNumber -> {
                secondNumber.clearFocus()
            }
        }
    }

    //Edit text 값에 따라서 이미지 보여주기
    private fun imgAppearAnim(view: EditText, num : Int) {
        val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
        when(num){
            1->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst4.startAnimation(animAppear)
                    imgFirst4.alpha = 1.0f
                }
                else {
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                }
            }
            2->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst9.startAnimation(animAppear)
                    imgFirst9.alpha = 1.0f
                    imgFirst10.startAnimation(animAppear)
                    imgFirst10.alpha = 1.0f
                }
                else {
                    imgSecond11.startAnimation(animAppear)
                    imgSecond11.alpha = 1.0f
                    imgSecond12.startAnimation(animAppear)
                    imgSecond12.alpha = 1.0f
                }
            }
            3->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst9.startAnimation(animAppear)
                    imgFirst9.alpha = 1.0f
                    imgFirst10.startAnimation(animAppear)
                    imgFirst10.alpha = 1.0f
                }
                else {
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond9.startAnimation(animAppear)
                    imgSecond9.alpha = 1.0f
                    imgSecond10.startAnimation(animAppear)
                    imgSecond10.alpha = 1.0f
                }
            }
            4->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst3.startAnimation(animAppear)
                    imgFirst3.alpha = 1.0f
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst9.startAnimation(animAppear)
                    imgFirst9.alpha = 1.0f
                    imgFirst10.startAnimation(animAppear)
                    imgFirst10.alpha = 1.0f
                }
                else {
                    imgSecond3.startAnimation(animAppear)
                    imgSecond3.alpha = 1.0f
                    imgSecond5.startAnimation(animAppear)
                    imgSecond5.alpha = 1.0f
                    imgSecond11.startAnimation(animAppear)
                    imgSecond11.alpha = 1.0f
                    imgSecond12.startAnimation(animAppear)
                    imgSecond12.alpha = 1.0f
                }
            }
            5->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst2.startAnimation(animAppear)
                    imgFirst2.alpha = 1.0f
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst8.startAnimation(animAppear)
                    imgFirst8.alpha = 1.0f
                    imgFirst9.startAnimation(animAppear)
                    imgFirst9.alpha = 1.0f
                    imgFirst10.startAnimation(animAppear)
                    imgFirst10.alpha = 1.0f
                }
                else {
                    imgSecond1.startAnimation(animAppear)
                    imgSecond1.alpha = 1.0f
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond7.startAnimation(animAppear)
                    imgSecond7.alpha = 1.0f
                    imgSecond9.startAnimation(animAppear)
                    imgSecond9.alpha = 1.0f
                    imgSecond10.startAnimation(animAppear)
                    imgSecond10.alpha = 1.0f
                }
            }
            6->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst1.startAnimation(animAppear)
                    imgFirst1.alpha = 1.0f
                    imgFirst2.startAnimation(animAppear)
                    imgFirst2.alpha = 1.0f
                    imgFirst4.startAnimation(animAppear)
                    imgFirst4.alpha = 1.0f
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst7.startAnimation(animAppear)
                    imgFirst7.alpha = 1.0f
                    imgFirst8.startAnimation(animAppear)
                    imgFirst8.alpha = 1.0f
                }
                else {
                    imgSecond0.startAnimation(animAppear)
                    imgSecond0.alpha = 1.0f
                    imgSecond1.startAnimation(animAppear)
                    imgSecond1.alpha = 1.0f
                    imgSecond3.startAnimation(animAppear)
                    imgSecond3.alpha = 1.0f
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond6.startAnimation(animAppear)
                    imgSecond6.alpha = 1.0f
                    imgSecond7.startAnimation(animAppear)
                    imgSecond7.alpha = 1.0f
                }
            }
            7->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst0.startAnimation(animAppear)
                    imgFirst0.alpha = 1.0f
                    imgFirst1.startAnimation(animAppear)
                    imgFirst1.alpha = 1.0f
                    imgFirst3.startAnimation(animAppear)
                    imgFirst3.alpha = 1.0f
                    imgFirst4.startAnimation(animAppear)
                    imgFirst4.alpha = 1.0f
                    imgFirst6.startAnimation(animAppear)
                    imgFirst6.alpha = 1.0f
                    imgFirst7.startAnimation(animAppear)
                    imgFirst7.alpha = 1.0f
                    imgFirst8.startAnimation(animAppear)
                    imgFirst8.alpha = 1.0f
                }
                else {
                    imgSecond0.startAnimation(animAppear)
                    imgSecond0.alpha = 1.0f
                    imgSecond1.startAnimation(animAppear)
                    imgSecond1.alpha = 1.0f
                    imgSecond3.startAnimation(animAppear)
                    imgSecond3.alpha = 1.0f
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond6.startAnimation(animAppear)
                    imgSecond6.alpha = 1.0f
                    imgSecond7.startAnimation(animAppear)
                    imgSecond7.alpha = 1.0f
                    imgSecond8.startAnimation(animAppear)
                    imgSecond8.alpha = 1.0f
                }
            }
            8->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst0.startAnimation(animAppear)
                    imgFirst0.alpha = 1.0f
                    imgFirst1.startAnimation(animAppear)
                    imgFirst1.alpha = 1.0f
                    imgFirst3.startAnimation(animAppear)
                    imgFirst3.alpha = 1.0f
                    imgFirst4.startAnimation(animAppear)
                    imgFirst4.alpha = 1.0f
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst6.startAnimation(animAppear)
                    imgFirst6.alpha = 1.0f
                    imgFirst7.startAnimation(animAppear)
                    imgFirst7.alpha = 1.0f
                    imgFirst8.startAnimation(animAppear)
                    imgFirst8.alpha = 1.0f
                }
                else {
                    imgSecond0.startAnimation(animAppear)
                    imgSecond0.alpha = 1.0f
                    imgSecond1.startAnimation(animAppear)
                    imgSecond1.alpha = 1.0f
                    imgSecond3.startAnimation(animAppear)
                    imgSecond3.alpha = 1.0f
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond5.startAnimation(animAppear)
                    imgSecond5.alpha = 1.0f
                    imgSecond6.startAnimation(animAppear)
                    imgSecond6.alpha = 1.0f
                    imgSecond7.startAnimation(animAppear)
                    imgSecond7.alpha = 1.0f
                    imgSecond8.startAnimation(animAppear)
                    imgSecond8.alpha = 1.0f
                }
            }
            9->{
                clearAllImg(view)
                if(view == firstNumber) {
                    imgFirst0.startAnimation(animAppear)
                    imgFirst0.alpha = 1.0f
                    imgFirst1.startAnimation(animAppear)
                    imgFirst1.alpha = 1.0f
                    imgFirst2.startAnimation(animAppear)
                    imgFirst2.alpha = 1.0f
                    imgFirst3.startAnimation(animAppear)
                    imgFirst3.alpha = 1.0f
                    imgFirst4.startAnimation(animAppear)
                    imgFirst4.alpha = 1.0f
                    imgFirst5.startAnimation(animAppear)
                    imgFirst5.alpha = 1.0f
                    imgFirst6.startAnimation(animAppear)
                    imgFirst6.alpha = 1.0f
                    imgFirst7.startAnimation(animAppear)
                    imgFirst7.alpha = 1.0f
                    imgFirst8.startAnimation(animAppear)
                    imgFirst8.alpha = 1.0f
                }
                else {
                    imgSecond0.startAnimation(animAppear)
                    imgSecond0.alpha = 1.0f
                    imgSecond1.startAnimation(animAppear)
                    imgSecond1.alpha = 1.0f
                    imgSecond2.startAnimation(animAppear)
                    imgSecond2.alpha = 1.0f
                    imgSecond3.startAnimation(animAppear)
                    imgSecond3.alpha = 1.0f
                    imgSecond4.startAnimation(animAppear)
                    imgSecond4.alpha = 1.0f
                    imgSecond5.startAnimation(animAppear)
                    imgSecond5.alpha = 1.0f
                    imgSecond6.startAnimation(animAppear)
                    imgSecond6.alpha = 1.0f
                    imgSecond7.startAnimation(animAppear)
                    imgSecond7.alpha = 1.0f
                    imgSecond8.startAnimation(animAppear)
                    imgSecond8.alpha = 1.0f
                }
            }
            0->{
                clearAllImg(view)
                if(view == firstNumber) {

                }
                else {

                }
            }
            plusOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "＋"
            }
            minusOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "－"
            }
            multiplyOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "×"
            }
            divideOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "÷"
            }
        }
    }

    //화면의 이미지 모두 지워주기
    private fun clearAllImg(view: EditText){
        if(view == firstNumber){
            imgFirst0.alpha = 0.0f
            imgFirst1.alpha = 0.0f
            imgFirst2.alpha = 0.0f
            imgFirst3.alpha = 0.0f
            imgFirst4.alpha = 0.0f
            imgFirst5.alpha = 0.0f
            imgFirst6.alpha = 0.0f
            imgFirst7.alpha = 0.0f
            imgFirst8.alpha = 0.0f
            imgFirst9.alpha = 0.0f
            imgFirst10.alpha = 0.0f
        }
        else{
            imgSecond0.alpha = 0.0f
            imgSecond1.alpha = 0.0f
            imgSecond2.alpha = 0.0f
            imgSecond3.alpha = 0.0f
            imgSecond4.alpha = 0.0f
            imgSecond5.alpha = 0.0f
            imgSecond6.alpha = 0.0f
            imgSecond7.alpha = 0.0f
            imgSecond8.alpha = 0.0f
            imgSecond9.alpha = 0.0f
            imgSecond10.alpha = 0.0f
            imgSecond11.alpha = 0.0f
            imgSecond12.alpha = 0.0f
        }
    }
}
