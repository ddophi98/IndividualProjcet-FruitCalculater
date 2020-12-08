package kr.co.ddophi.calculaterforkids

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
        btnSetting()

        blank1.setOnClickListener {

        }
    }

    private fun plusAnim (view: EditText, num : Int) {

    }
    private fun minusAnim (view: EditText, num : Int) {

    }
    private fun multiplyAnim (view: EditText, num : Int) {

    }
    private fun divideAnim (view: EditText, num : Int) {

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

    //커스텀 키보드 버튼 동작 세팅(다 작성 안하고 = 눌렀을 때 알림 필요)
    private fun btnSetting () {
        val btnNumbers = arrayListOf<ImageButton>(btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)

        for((idx, btn) in btnNumbers.withIndex()){
            btn.setOnClickListener {
                if (firstNumber.hasFocus()) {
                    firstNumber.setText(idx.toString())
                    moveFocus(firstNumber)
                    imgAppearAnim(firstNumber, idx)
                }else if(secondNumber.hasFocus()){
                    secondNumber.setText(idx.toString())
                    moveFocus(secondNumber)
                    imgAppearAnim(secondNumber, idx)
                }else if(!firstNumber.hasFocus() && !secondNumber.hasFocus() && !operator.hasFocus()){
                    clearAll()
                    firstNumber.setText(idx.toString())
                    moveFocus(firstNumber)
                    imgAppearAnim(firstNumber, idx)
                }
            }
        }

        btnPlus.setOnClickListener {
            if(operator.hasFocus()) {
                operator.setText("＋")
                moveFocus(operator)
                imgAppearAnim(operator, plusOperator)
            }
        }
        btnMinus.setOnClickListener {
            if(operator.hasFocus()) {
                operator.setText("－")
                moveFocus(operator)
                imgAppearAnim(operator, minusOperator)
            }
        }
        btnMultiply.setOnClickListener {
            if(operator.hasFocus()) {
                operator.setText("×")
                moveFocus(operator)
                imgAppearAnim(operator, multiplyOperator)
            }
        }
        btnDivide.setOnClickListener {
            if(operator.hasFocus()) {
                operator.setText("÷")
                moveFocus(operator)
                imgAppearAnim(operator, divideOperator)
            }
        }

        btnResult.setOnClickListener {
            if(firstNumber.text.toString() == ""){

            }else if(operator.text.toString() == ""){

            }else if(secondNumber.text.toString() == ""){

            }else{
                showResult()
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

    //Edit text 값에 따라서 이미지 보여주기 (0일 때의 그림 필요)
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
                txtOperator.visibility = View.VISIBLE
            }
            minusOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "－"
                txtOperator.visibility = View.VISIBLE
            }
            multiplyOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "×"
                txtOperator.visibility = View.VISIBLE
            }
            divideOperator->{
                txtOperator.startAnimation(animAppear)
                txtOperator.text = "÷"
                txtOperator.visibility = View.VISIBLE
            }
        }
    }

    //해당하는 쪽의 그림 지워주기
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

    //화면에 그려진 것들을 모두 지워주기
    private fun clearAll() {
        clearAllImg(firstNumber)
        clearAllImg(secondNumber)
        txtOperator.visibility = View.INVISIBLE
        txtResult.visibility = View.INVISIBLE
        txtResultNum.text = ""
        firstNumber.setText("")
        operator.setText("")
        secondNumber.setText("")
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

        val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
        txtResult.startAnimation(animAppear)
        txtResult.visibility = View.VISIBLE
        txtResultNum.startAnimation(animAppear)
        txtResultNum.text = "$result"

    }
}
