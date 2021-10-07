package main
import (
	"fmt"
	"strings"
)
func main() {
	hello := "hello word"

	helloSplit := strings.Split(hello,"")

	str4 :=strings.Join(helloSplit,";")

	fmt.Printf("%s\n",str4)
}