package kr.co.ddophi.calculaterforkids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val plusOperator = 10
    private val minusOperator = 11
    private val multiplyOperator = 12
    private val divideOperator = 13
    var isAnimRunning = false
    lateinit var resultAnimJob : Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideKeyboard()
        btnSetting()

        blank1.setOnClickListener {
           clearBottomImg()
        }
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
                    clearBottomImg()
                    firstNumber.setText(idx.toString())
                    moveFocus(firstNumber)
                    imgAppearAnim(firstNumber, idx)
                }else if(secondNumber.hasFocus()){
                    clearBottomImg()
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
                clearBottomImg()
                operator.setText("＋")
                moveFocus(operator)
                imgAppearAnim(operator, plusOperator)
            }
        }
        btnMinus.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("－")
                moveFocus(operator)
                imgAppearAnim(operator, minusOperator)
            }
        }
        btnMultiply.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("×")
                moveFocus(operator)
                imgAppearAnim(operator, multiplyOperator)
            }
        }
        btnDivide.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("÷")
                moveFocus(operator)
                imgAppearAnim(operator, divideOperator)
            }
        }

        btnResult.setOnClickListener {
            if(firstNumber.text.toString() == ""){

            }else if(operator.text.toString() == ""){

            }else if(secondNumber.text.toString() == ""){

            }else if(!isAnimRunning){
                clearBottomImg()
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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
                clearTopImg(view)
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

    //화면 위쪽에 그려진 것들 지워주기 - 왼쪽/오른쪽 선택
    private fun clearTopImg(view: EditText){
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

    //화면 아래쪽에 그려진 것들 지워주기
    private fun clearBottomImg() {
        val resultImg = arrayListOf(testImg0, testImg1, testImg2, testImg3, testImg4, testImg5, testImg6, testImg7, testImg8)
        if(isAnimRunning){
            resultAnimJob.cancel()
            isAnimRunning = false
        }
        numForMinus.text = ""
        txtResult.text = ""
        for(img in resultImg)
            img.alpha = 0.0f
    }

    //화면에 그려진 것들을 모두 지워주기
    private fun clearAll() {
        clearBottomImg()
        clearTopImg(firstNumber)
        clearTopImg(secondNumber)
        txtOperator.visibility = View.INVISIBLE
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
        val calculateAnim = CalculateAnimation()

        when(op){
            "＋" -> {
                result = firstNum + secondNum
                calculateAnim.plus(firstNum, secondNum, result)
            }
            "－" -> {
                result = firstNum - secondNum
                calculateAnim.minus(firstNum, secondNum, result)
            }
            "×" -> {
                result = firstNum * secondNum
                calculateAnim.multiply(firstNum, secondNum, result)
            }
            "÷" -> {
                result = firstNum / secondNum
                calculateAnim.divide(firstNum, secondNum, result)
            }
        }
    }

    //계산 과정 보여주는 애니메이션 모음
    inner class CalculateAnimation() {

        //더하기 애니메이션
        fun plus (firstNum : Int, secondNum : Int, result: Int) {
            resultAnimJob = GlobalScope.launch(Dispatchers.Main) {
                isAnimRunning = true
                appearImg(testImg1, firstNum)
                appearImg(testImg2, secondNum)
                delay(1000)
                appearResult(result, 0)
                delay(1000)
                isAnimRunning = false
            }
        }
        //빼기 애니메이션
        fun minus (firstNum : Int, secondNum : Int, result: Int) {
            var num = firstNum
            resultAnimJob = GlobalScope.launch(Dispatchers.Main){
                isAnimRunning = true
                appearImg(testImg6, num)
                numForMinus.text = "$num"
                delay(700)
                for(i in 0 until secondNum){
                    appearImg(testImg6, --num)
                    numForMinus.text = "$num"
                    delay(700)
                }
                appearResult(result, 0)
                delay(1000)
                isAnimRunning = false
            }
        }
        //곱하기 애니메이션
        fun multiply (firstNum : Int, secondNum : Int, result: Int) {
            resultAnimJob = GlobalScope.launch(Dispatchers.Main) {
                isAnimRunning = true
                when(secondNum){
                    1 -> appearImg(testImg6, firstNum)
                    2 -> {
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                    }
                    3 -> {
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg6, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                    }
                    4 -> {
                        appearImg(testImg0, firstNum)
                        delay(700)
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg3, firstNum)
                    }
                    5 -> {
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg6, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                    }
                    6 -> {
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg4, firstNum)
                        delay(700)
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                        delay(700)
                        appearImg(testImg8, firstNum)
                    }
                    7 -> {
                        appearImg(testImg0, firstNum)
                        delay(700)
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg3, firstNum)
                        delay(700)
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg6, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                    }
                    8 -> {
                        appearImg(testImg0, firstNum)
                        delay(700)
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg3, firstNum)
                        delay(700)
                        appearImg(testImg4, firstNum)
                        delay(700)
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                        delay(700)
                        appearImg(testImg8, firstNum)
                    }
                    9 -> {
                        appearImg(testImg0, firstNum)
                        delay(700)
                        appearImg(testImg1, firstNum)
                        delay(700)
                        appearImg(testImg2, firstNum)
                        delay(700)
                        appearImg(testImg3, firstNum)
                        delay(700)
                        appearImg(testImg4, firstNum)
                        delay(700)
                        appearImg(testImg5, firstNum)
                        delay(700)
                        appearImg(testImg6, firstNum)
                        delay(700)
                        appearImg(testImg7, firstNum)
                        delay(700)
                        appearImg(testImg8, firstNum)
                    }
                }
                delay(700)
                appearResult(result, 0)
                delay(1000)
                isAnimRunning = false
            }
        }
        //나누기 애니메이션
        fun divide (firstNum : Int, secondNum : Int, result: Int) {
            val remain = firstNum % secondNum
            resultAnimJob = GlobalScope.launch(Dispatchers.Main) {
                isAnimRunning = true
                when(result){
                    1 -> appearImg(testImg6, secondNum)
                    2 -> {
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                    }
                    3 -> {
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg6, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                    }
                    4 -> {
                        appearImg(testImg0, secondNum)
                        delay(700)
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg3, secondNum)
                    }
                    5 -> {
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg6, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                    }
                    6 -> {
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg4, secondNum)
                        delay(700)
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                        delay(700)
                        appearImg(testImg8, secondNum)
                    }
                    7 -> {
                        appearImg(testImg0, secondNum)
                        delay(700)
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg3, secondNum)
                        delay(700)
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg6, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                    }
                    8 -> {
                        appearImg(testImg0, secondNum)
                        delay(700)
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg3, secondNum)
                        delay(700)
                        appearImg(testImg4, secondNum)
                        delay(700)
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                        delay(700)
                        appearImg(testImg8, secondNum)
                    }
                    9 -> {
                        appearImg(testImg0, secondNum)
                        delay(700)
                        appearImg(testImg1, secondNum)
                        delay(700)
                        appearImg(testImg2, secondNum)
                        delay(700)
                        appearImg(testImg3, secondNum)
                        delay(700)
                        appearImg(testImg4, secondNum)
                        delay(700)
                        appearImg(testImg5, secondNum)
                        delay(700)
                        appearImg(testImg6, secondNum)
                        delay(700)
                        appearImg(testImg7, secondNum)
                        delay(700)
                        appearImg(testImg8, secondNum)
                    }
                }
                delay(700)
                appearResult(result, remain)
                delay(1000)
                isAnimRunning = false
            }
        }


        //아래쪽 부분에 결과 그림 보여주기
        private fun appearImg(imgPos : ImageView, num: Int){
            when {
                num > 0 -> imgPos.alpha = 1.0f
                num < 0 -> imgPos.alpha = 0.3f
                else -> imgPos.alpha = 0.0f
            }
            when(num){
                -1, 1 -> imgPos.setImageResource(R.drawable.test_img1)
                -2, 2 -> imgPos.setImageResource(R.drawable.test_img2)
                -3, 3 -> imgPos.setImageResource(R.drawable.test_img3)
                -4, 4 -> imgPos.setImageResource(R.drawable.test_img4)
                -5, 5 -> imgPos.setImageResource(R.drawable.test_img5)
                -6, 6 -> imgPos.setImageResource(R.drawable.test_img6)
                -7, 7 -> imgPos.setImageResource(R.drawable.test_img7)
                -8, 8 -> imgPos.setImageResource(R.drawable.test_img8)
                -9, 9 -> imgPos.setImageResource(R.drawable.test_img9)
            }
        }

        //아래쪽 부분에 결과값 보여주기
        private fun appearResult(num: Int, remain: Int){
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
            txtResult.startAnimation(animAppear)
            if(remain == 0) {
                txtResult.text = "= "
                txtResult.append("$num")
            }else{
                txtResult.text = "= "
                txtResult.append("$num")
                txtResult.append(" (나머지: ")
                txtResult.append("$remain")
                txtResult.append(")")
            }
        }
    }
}


