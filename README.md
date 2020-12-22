# CurrencyApp
Kotlin - LiveData - Retrofit - MVVM - Fixor APi - Koin - Repository

Question #1

2.25 4.50 1.25 5=27
find Operations

start 2.25 + 4.50 = 6.75
then 6.75 / 1.25 = 5.4
then 5.4 * 5 = 27

Result =>  (2.25 + 4.50) / 1.25 * 5 = 27
____________________________________________________

Question #2




____________________________________________________

Question #3
get two methods for generate nth Febonacci numbers

First Approach 
fun getFebonacciNums(firstN : Int) : ArrayList<Int>{
    var list = ArrayList<Int>()
    for(i in 0..firstN){
       if(i == 0 || i == 1){
           list.add(i)
       }else{
           var num = list[i -2] + list[i -1]
           list.add(num)
       }
    }
    return list
}

Second Approach
fun fib(n :Int) : Int{
    if(n <= 1)
    return n
    return fib(n -1) + fib(n-2)
}
_____________________________________________________
