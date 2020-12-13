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

                if(secondNumber.text.isNotEmpty()){
                    imgAppearAnim(secondNumber, secondNumber.text.toString().toInt())
                }
            }
        }
        btnDivide.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("÷")
                moveFocus(operator)
                imgAppearAnim(operator, divideOperator)

                if(secondNumber.text.isNotEmpty()){
                    imgAppearAnim(secondNumber, secondNumber.text.toString().toInt())
                }
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

    //Edit text 값에 따라서 이미지 보여주기
    private fun imgAppearAnim(view: EditText, num : Int) {
        val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
        when(num){
            1->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "1"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple1)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple1)
                    }
                }
            }
            2->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "2"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple2)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple2)
                    }
                }
            }
            3->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "3"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple3)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple3)
                    }
                }
            }
            4->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "4"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple4)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple4)
                    }
                }
            }
            5->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "5"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple5)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple5)
                    }
                }
            }
            6->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "6"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple6)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple6)
                    }
                }
            }
            7->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "7"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple7)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple7)
                    }
                }
            }
            8->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "8"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple8)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple8)
                    }
                }
            }
            9->{
                clearTopImg(view)
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "9"
                    txtShow.startAnimation(animAppear)
                }else {
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.apple9)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.apple9)
                    }
                }
            }
            0->{
                if(operator.text.toString() == "×" || operator.text.toString() == "÷"){
                    imgShow2.visibility = View.GONE
                    txtShow.visibility = View.VISIBLE
                    txtShow.text = "0"
                    txtShow.startAnimation(animAppear)
                }else {
                    clearTopImg(view)
                    if (view == firstNumber) {
                        imgShow1.startAnimation(animAppear)
                        imgShow1.alpha = 1.0f
                        imgShow1.setImageResource(R.drawable.fruit_empty)
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(R.drawable.fruit_empty)
                    }
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
        if(view == firstNumber)
            imgShow1.alpha = 0.0f
        else {
            txtShow.visibility = View.GONE
            imgShow2.visibility = View.VISIBLE
            imgShow2.alpha = 0.0f
        }
    }

    //화면 아래쪽에 그려진 것들 지워주기
    private fun clearBottomImg() {
        val resultImg = arrayListOf(testImg0, testImg1, testImg2, testImg3, testImg5, testImg6, testImg7, testImg8, testImg9)
        if(isAnimRunning){
            resultAnimJob.cancel()
            isAnimRunning = false
        }
        numForImg.text = ""
        txtResult.text = ""
        for(img in resultImg)
            img.alpha = 0.0f
        testImg0.visibility = View.VISIBLE
        testImg4.visibility = View.VISIBLE
        testImg5.visibility = View.VISIBLE
        testImg9.visibility = View.VISIBLE
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
                if(secondNum == 0){

                }else {
                    result = firstNum / secondNum
                    calculateAnim.divide(firstNum, secondNum, result)
                }
            }
        }
    }

    //계산 과정 보여주는 애니메이션 모음
    inner class CalculateAnimation() {

        private val animChangeTime1 = 600L
        private val animChangeTime2 = 600L

        //더하기 애니메이션
        fun plus (firstNum : Int, secondNum : Int, result: Int) {
            var num = firstNum
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_fast_anim)
            resultAnimJob = GlobalScope.launch(Dispatchers.Main){
                isAnimRunning = true
                testImg0.visibility = View.GONE
                appearImg(testImg7, num)
                testImg7.startAnimation(animAppear)
                numForImg.text = "$num"
                numForImg.startAnimation(animAppear)
                delay(700)
                for(i in 0 until secondNum){
                    appearImg(testImg7, ++num)
                    numForImg.text = "$num"
                    delay(animChangeTime1)
                }
                appearResult(result, 0)
                delay(1000)
                isAnimRunning = false
            }
        }
        //빼기 애니메이션
        fun minus (firstNum : Int, secondNum : Int, result: Int) {
            var num = firstNum
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_fast_anim)
            resultAnimJob = GlobalScope.launch(Dispatchers.Main){
                isAnimRunning = true
                testImg0.visibility = View.GONE
                appearImg(testImg7, num)
                testImg7.startAnimation(animAppear)
                numForImg.text = "$num"
                numForImg.startAnimation(animAppear)
                delay(700)
                for(i in 0 until secondNum){
                    appearImg(testImg7, --num)
                    numForImg.text = "$num"
                    delay(animChangeTime1)
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
                if (firstNum == 0 || secondNum == 0) {
                    testImg0.visibility = View.GONE
                    appearImg(testImg7, 0)
                } else{
                    when (secondNum) {
                        1 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg7, firstNum)
                        }
                        2 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        3 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        4 -> {
                            appearImg(testImg1, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        5 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg5, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg9, firstNum)
                        }
                        6 -> {
                            appearImg(testImg1, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        7 -> {
                            testImg9.visibility = View.GONE
                            appearImg(testImg1, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        8 -> {
                            testImg4.visibility = View.GONE
                            testImg9.visibility = View.GONE
                            appearImg(testImg0, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg1, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                        }
                        9 -> {
                            testImg4.visibility = View.GONE
                            appearImg(testImg0, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg1, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, firstNum)
                            delay(animChangeTime2)
                            appearImg(testImg9, firstNum)
                        }
                    }
                }
                delay(animChangeTime2)
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
                if(firstNum == 0 || (firstNum < secondNum)){
                    testImg0.visibility = View.GONE
                    appearImg(testImg7, 0)
                }else if(secondNum == 0){

                }else {
                    when (result) {
                        1 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg7, secondNum)
                        }
                        2 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        3 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        4 -> {
                            appearImg(testImg1, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        5 -> {
                            testImg0.visibility = View.GONE
                            appearImg(testImg5, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg9, secondNum)
                        }
                        6 -> {
                            appearImg(testImg1, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        7 -> {
                            testImg9.visibility = View.GONE
                            appearImg(testImg1, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        8 -> {
                            testImg4.visibility = View.GONE
                            testImg9.visibility = View.GONE
                            appearImg(testImg0, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg1, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                        }
                        9 -> {
                            testImg4.visibility = View.GONE
                            appearImg(testImg0, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg1, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg2, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg3, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg5, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg6, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg7, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg8, secondNum)
                            delay(animChangeTime2)
                            appearImg(testImg9, secondNum)
                        }
                    }
                }
                delay(animChangeTime2)
                appearResult(result, remain)
                delay(1000)
                isAnimRunning = false
            }
        }


        //아래쪽 부분에 결과 그림 보여주기
        private fun appearImg(imgPos : ImageView, num: Int){
            when {
                num < 0 -> imgPos.alpha = 0.3f
                num > 9 -> {
                    imgPos.alpha = 1.0f
                    testImg5.visibility = View.GONE
                    testImg9.visibility = View.GONE
                }
                else -> imgPos.alpha = 1.0f
            }
            when(num){
                0 -> imgPos.setImageResource(R.drawable.fruit_empty)
                -1, 1 -> imgPos.setImageResource(R.drawable.apple1)
                -2, 2 -> imgPos.setImageResource(R.drawable.apple2)
                -3, 3 -> imgPos.setImageResource(R.drawable.apple3)
                -4, 4 -> imgPos.setImageResource(R.drawable.apple4)
                -5, 5 -> imgPos.setImageResource(R.drawable.apple5)
                -6, 6 -> imgPos.setImageResource(R.drawable.apple6)
                -7, 7 -> imgPos.setImageResource(R.drawable.apple7)
                -8, 8 -> imgPos.setImageResource(R.drawable.apple8)
                -9, 9 -> imgPos.setImageResource(R.drawable.apple9)
                10 -> imgPos.setImageResource(R.drawable.apple10)
                11 -> imgPos.setImageResource(R.drawable.apple11)
                12 -> imgPos.setImageResource(R.drawable.apple12)
                13 -> imgPos.setImageResource(R.drawable.apple13)
                14 -> imgPos.setImageResource(R.drawable.apple14)
                15 -> imgPos.setImageResource(R.drawable.apple15)
                16 -> imgPos.setImageResource(R.drawable.apple16)
                17 -> imgPos.setImageResource(R.drawable.apple17)
                18 -> imgPos.setImageResource(R.drawable.apple18)
            }
        }

        //아래쪽 부분에 결과값 보여주기
        private fun appearResult(num: Int, remain: Int){
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
            txtResult.startAnimation(animAppear)
            txtResult.text = "= "
            txtResult.append("$num")
            txtResult.append(" (나머지: ")
            txtResult.append("$remain")
            txtResult.append(")")
        }
    }
}


