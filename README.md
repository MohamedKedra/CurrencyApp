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
Find Anagram with high order functions

        isAnagram("debit card","bad credit",{str1:String,str2:String -> 
                  val hmap1 = HashMap<Char, Int?>()
                  val hmap2 = HashMap<Char, Int?>()
                  val arr1 = str1.toCharArray()
                  val arr2 = str2.toCharArray()

                  for (i in arr1.indices) {
                      if (hmap1[arr1[i]] == null) {
                         hmap1[arr1.get(i)] = 1
                      } else {
                        var c: Int = hmap1[arr1[i]] as Int
                        hmap1[arr1[i]] = ++c
                      }
                  }

                 for (j in arr2.indices) {
                     if (hmap2[arr2[j]] == null){
                        hmap2[arr2.get(j)] = 1
                     }else {
                       var d: Int = hmap2[arr2[j]] as Int
                       hmap2[arr2.get(j)] = ++d
                     }
                 }
        
                 hmap1 == hmap2
                 })
    
       fun isAnagram(str1: String, str2: String, hasAnagram: (String,String) -> Boolean){ 
            print(hasAnagram(str1,str2))
       }



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
