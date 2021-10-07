package main

import (
	"fmt"
	"math"
)

var a string
var n int = 5
var temp uint8 = 0
func main() {
	a = "G"
	print(a)
	f1()

}
func f1() {
	a := "O"
	print(a)
	f2()
}
func f2() {
	print(a)
}
func Uint8FromInt(n int) (uint8, error) {
	if 0 <= n && n <= math.MaxUint8 { // conversion is safe
		return uint8(n), nil
	}
	return 0, fmt.Errorf("%d is out of the uint8 range", n)
}