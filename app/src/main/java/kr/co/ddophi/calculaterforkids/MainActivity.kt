package kr.co.ddophi.calculaterforkids

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val plusOperator = 10
    private val minusOperator = 11
    private val multiplyOperator = 12
    private val divideOperator = 13
    private var isAnimRunning = false
    private var randomNum = 0
    private var toast:Toast? = null
    private var isFruitShowed = arrayListOf(false, false, false, false, false)
    lateinit var resultAnimJob : Job
    lateinit var fruitList: ArrayList<ArrayList<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFruitList()
        hideKeyboard()
        btnSetting()
    }

    //상황에 따라 토스트 메시지 띄우기
    private fun showToast(num : Int){
        val toastMessage0 = resources.getString(R.string.toastMessage0)
        val toastMessage1 = resources.getString(R.string.toastMessage1)
        val toastMessage2 = resources.getString(R.string.toastMessage2)
        val toastMessage3 = resources.getString(R.string.toastMessage3)
        val toastMessage4 = resources.getString(R.string.toastMessage4)

        if(toast != null)
            toast!!.cancel()
        when(num){
            0 -> toast = Toast.makeText(this, toastMessage0, Toast.LENGTH_SHORT)
            1 -> toast = Toast.makeText(this, toastMessage1, Toast.LENGTH_SHORT)
            2 -> toast = Toast.makeText(this, toastMessage2, Toast.LENGTH_SHORT)
            3 -> toast = Toast.makeText(this, toastMessage3, Toast.LENGTH_SHORT)
            4 -> toast = Toast.makeText(this, toastMessage4, Toast.LENGTH_SHORT)
        }
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        toast!!.show()
    }

    //여러 과일 이미지들 불러오기
    private fun initFruitList(){
        val fruit = Fruit()
        fruitList = arrayListOf(fruit.apple, fruit.banana, fruit.grape, fruit.kmelon, fruit.orange)
    }

    //어떤 과일을 보여줄지 랜덤으로 결정하기
    private fun setRandomNum(){
        val random = Random()
        var isAllTrue = true

        for(i in isFruitShowed){
            if(!i){
                isAllTrue = false
                break
            }
        }

        if(isAllTrue){
            for((idx, i) in isFruitShowed.withIndex()){
                isFruitShowed[idx] = false
            }
        }

        do {
            randomNum = random.nextInt(5)
        }
        while(isFruitShowed[randomNum])

        isFruitShowed[randomNum] = true
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

    //커스텀 키보드 버튼 동작 세팅
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
                }else if(operator.hasFocus()) {
                    showToast(2)
                }
                else if(!firstNumber.hasFocus() && !secondNumber.hasFocus() && !operator.hasFocus()){
                    clearAll()
                    firstNumber.setText(idx.toString())
                    moveFocus(firstNumber)
                    imgAppearAnim(firstNumber, idx)
                }
            }
        }

        btnCancel.setOnClickListener {
            clearAll()
            firstNumber.clearFocus()
            secondNumber.clearFocus()
            operator.clearFocus()
        }

        btnPlus.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("＋")
                moveFocus(operator)
                imgAppearAnim(operator, plusOperator)
            }else if(firstNumber.hasFocus() || secondNumber.hasFocus()){
                showToast(0)
            }
        }
        btnMinus.setOnClickListener {
            if(operator.hasFocus()) {
                clearBottomImg()
                operator.setText("－")
                moveFocus(operator)
                imgAppearAnim(operator, minusOperator)
            }else if(firstNumber.hasFocus() || secondNumber.hasFocus()){
                showToast(0)
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
            }else if(firstNumber.hasFocus() || secondNumber.hasFocus()){
                showToast(0)
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
            }else if(firstNumber.hasFocus() || secondNumber.hasFocus()){
                showToast(0)
            }
        }

        btnResult.setOnClickListener {
            if(firstNumber.text.toString() == ""){
                showToast(1)
            }else if(operator.text.toString() == ""){
                showToast(2)
            }else if(secondNumber.text.toString() == ""){
                showToast(3)
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
                        imgShow1.setImageResource(fruitList[randomNum][1])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][1])
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
                        imgShow1.setImageResource(fruitList[randomNum][2])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][2])
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
                        imgShow1.setImageResource(fruitList[randomNum][3])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][3])
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
                        imgShow1.setImageResource(fruitList[randomNum][4])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][4])
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
                        imgShow1.setImageResource(fruitList[randomNum][5])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][5])
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
                        imgShow1.setImageResource(fruitList[randomNum][6])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][6])
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
                        imgShow1.setImageResource(fruitList[randomNum][7])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][7])
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
                        imgShow1.setImageResource(fruitList[randomNum][8])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][8])
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
                        imgShow1.setImageResource(fruitList[randomNum][9])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][9])
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
                        imgShow1.setImageResource(fruitList[randomNum][0])
                    } else {
                        imgShow2.startAnimation(animAppear)
                        imgShow2.alpha = 1.0f
                        imgShow2.setImageResource(fruitList[randomNum][0])
                    }
                }
            }
            plusOperator->{
                operatorShow.startAnimation(animAppear)
                operatorShow.setImageResource(R.drawable.operator1)
                operatorShow.visibility = View.VISIBLE
            }
            minusOperator->{
                operatorShow.startAnimation(animAppear)
                operatorShow.setImageResource(R.drawable.operator2)
                operatorShow.visibility = View.VISIBLE
            }
            multiplyOperator->{
                operatorShow.startAnimation(animAppear)
                operatorShow.setImageResource(R.drawable.operator3)
                operatorShow.visibility = View.VISIBLE
            }
            divideOperator->{
                operatorShow.startAnimation(animAppear)
                operatorShow.setImageResource(R.drawable.operator4)
                operatorShow.visibility = View.VISIBLE
            }
        }
    }

    //화면 위쪽에 그려진 것들 지워주기 - 왼쪽/오른쪽 선택
    private fun clearTopImg(view: EditText){
        if(view == firstNumber) {
            imgShow1.alpha = 0.0f
        }
        else {
            txtShow.visibility = View.GONE
            imgShow2.visibility = View.VISIBLE
            imgShow2.alpha = 0.0f
        }
    }

    //화면 아래쪽에 그려진 것들 지워주기
    private fun clearBottomImg() {
        val resultImg = arrayListOf(imgCalculate0, imgCalculate1, imgCalculate2, imgCalculate3, imgCalculate5, imgCalculate6, imgCalculate7, imgCalculate8, imgCalculate9)
        if(isAnimRunning){
            resultAnimJob.cancel()
            isAnimRunning = false
        }
        txtResult.text = ""
        for(img in resultImg)
            img.alpha = 0.0f
        imgCalculate0.visibility = View.VISIBLE
        imgCalculate4.visibility = View.VISIBLE
        imgCalculate5.visibility = View.VISIBLE
        imgCalculate6.visibility = View.VISIBLE
        imgCalculate8.visibility = View.VISIBLE
        imgCalculate9.visibility = View.VISIBLE
    }

    //화면에 그려진 것들을 모두 지워주기
    private fun clearAll() {
        setRandomNum()
        clearBottomImg()
        clearTopImg(firstNumber)
        clearTopImg(secondNumber)
        operatorShow.visibility = View.INVISIBLE
        firstNumber.setText("")
        operator.setText("")
        secondNumber.setText("")
    }

    //계산 결과 보여주기
    private fun showResult(){
        val firstNum = firstNumber.text.toString().toInt()
        val secondNum = secondNumber.text.toString().toInt()
        val op = operator.text.toString()
        val result: Int?
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
                    showToast(4)
                }else {
                    result = firstNum / secondNum
                    calculateAnim.divide(firstNum, secondNum, result)
                }
            }
        }
    }

    //계산 과정 보여주는 애니메이션 모음
    inner class CalculateAnimation {

        private val animChangeTime1 = 600L
        private val animChangeTime2 = 600L

        //가운데 이미지 크게 보이게 하기 위해 주변 이미지 없애주기
        fun centerGone(){
            imgCalculate0.visibility = View.GONE
            imgCalculate5.visibility = View.GONE
            imgCalculate6.visibility = View.GONE
            imgCalculate8.visibility = View.GONE
            imgCalculate9.visibility = View.GONE
        }

        //더하기 애니메이션
        fun plus (firstNum : Int, secondNum : Int, result: Int) {
            var num = firstNum
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_fast_anim)
            resultAnimJob = GlobalScope.launch(Dispatchers.Main){
                isAnimRunning = true
                centerGone()
                appearImg(imgCalculate7, num)
                imgCalculate7.startAnimation(animAppear)
                delay(700)
                for(i in 0 until secondNum){
                    appearImg(imgCalculate7, ++num)
                    delay(animChangeTime1)
                }
                appearResult(result, 0, false)
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
                centerGone()
                appearImg(imgCalculate7, num)
                imgCalculate7.startAnimation(animAppear)
                delay(700)
                for(i in 0 until secondNum){
                    appearImg(imgCalculate7, --num)
                    delay(animChangeTime1)
                }
                appearResult(result, 0, false)
                delay(1000)
                isAnimRunning = false
            }
        }
        //곱하기 애니메이션
        fun multiply (firstNum : Int, secondNum : Int, result: Int) {
            resultAnimJob = GlobalScope.launch(Dispatchers.Main) {
                isAnimRunning = true
                if (firstNum == 0 || secondNum == 0) {
                    centerGone()
                    appearImg(imgCalculate7, 0)
                } else{
                    when (secondNum) {
                        1 -> {
                            centerGone()
                            appearImg(imgCalculate7, firstNum)
                        }
                        2 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        3 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        4 -> {
                            appearImg(imgCalculate1, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        5 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate5, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate9, firstNum)
                        }
                        6 -> {
                            appearImg(imgCalculate1, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        7 -> {
                            imgCalculate9.visibility = View.GONE
                            appearImg(imgCalculate1, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        8 -> {
                            imgCalculate4.visibility = View.GONE
                            imgCalculate9.visibility = View.GONE
                            appearImg(imgCalculate0, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate1, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                        }
                        9 -> {
                            imgCalculate4.visibility = View.GONE
                            appearImg(imgCalculate0, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate1, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, firstNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate9, firstNum)
                        }
                    }
                }
                delay(animChangeTime2)
                appearResult(result, 0, false)
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
                    imgCalculate0.visibility = View.GONE
                    appearImg(imgCalculate7, 0)
                }else {
                    when (result) {
                        1 -> {
                            centerGone()
                            appearImg(imgCalculate7, secondNum)
                        }
                        2 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        3 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        4 -> {
                            appearImg(imgCalculate1, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        5 -> {
                            imgCalculate0.visibility = View.GONE
                            appearImg(imgCalculate5, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate9, secondNum)
                        }
                        6 -> {
                            appearImg(imgCalculate1, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        7 -> {
                            imgCalculate9.visibility = View.GONE
                            appearImg(imgCalculate1, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        8 -> {
                            imgCalculate4.visibility = View.GONE
                            imgCalculate9.visibility = View.GONE
                            appearImg(imgCalculate0, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate1, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                        }
                        9 -> {
                            imgCalculate4.visibility = View.GONE
                            appearImg(imgCalculate0, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate1, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate2, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate3, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate5, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate6, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate7, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate8, secondNum)
                            delay(animChangeTime2)
                            appearImg(imgCalculate9, secondNum)
                        }
                    }
                }
                delay(animChangeTime2)
                appearResult(result, remain, true)
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
                    imgCalculate5.visibility = View.GONE
                    imgCalculate9.visibility = View.GONE
                }
                else -> imgPos.alpha = 1.0f
            }
            when(num){
                0 -> imgPos.setImageResource(R.drawable.fruit_empty)
                -1, 1 -> imgPos.setImageResource(fruitList[randomNum][1])
                -2, 2 -> imgPos.setImageResource(fruitList[randomNum][2])
                -3, 3 -> imgPos.setImageResource(fruitList[randomNum][3])
                -4, 4 -> imgPos.setImageResource(fruitList[randomNum][4])
                -5, 5 -> imgPos.setImageResource(fruitList[randomNum][5])
                -6, 6 -> imgPos.setImageResource(fruitList[randomNum][6])
                -7, 7 -> imgPos.setImageResource(fruitList[randomNum][7])
                -8, 8 -> imgPos.setImageResource(fruitList[randomNum][8])
                -9, 9 -> imgPos.setImageResource(fruitList[randomNum][9])
                10 -> imgPos.setImageResource(fruitList[randomNum][10])
                11 -> imgPos.setImageResource(fruitList[randomNum][11])
                12 -> imgPos.setImageResource(fruitList[randomNum][12])
                13 -> imgPos.setImageResource(fruitList[randomNum][13])
                14 -> imgPos.setImageResource(fruitList[randomNum][14])
                15 -> imgPos.setImageResource(fruitList[randomNum][15])
                16 -> imgPos.setImageResource(fruitList[randomNum][16])
                17 -> imgPos.setImageResource(fruitList[randomNum][17])
                18 -> imgPos.setImageResource(fruitList[randomNum][18])
            }
        }

        //아래쪽 부분에 결과값 보여주기
        private fun appearResult(num: Int, remain: Int, isDivide: Boolean){
            val animAppear = AnimationUtils.loadAnimation(applicationContext, R.anim.img_appear_anim)
            txtResult.startAnimation(animAppear)
            txtResult.text = "= "
            txtResult.append("$num")
            if(isDivide) {
                val remainText = resources.getString(R.string.remainder)
                txtResult.append(" (")
                txtResult.append(remainText)
                txtResult.append(": ")
                txtResult.append("$remain")
                txtResult.append(")")
            }
        }
    }
}


