package main

import "fmt"

//基本类型属于值类型，   int    float     bool   string，
//适用于指传递
func main() {
	var name int =2
	var name2 =0
	a :=2
	println(a)

	println(&name)
	name2=name
	println(&name2)

	fmt.Println("hello:",22)

}