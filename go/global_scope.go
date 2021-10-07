package main

var a = "G"

func main() {
	n()
	m()
	n()
}
func m() {
	println(a)
}

func n() {
	a := "0"
	println(a)
}
