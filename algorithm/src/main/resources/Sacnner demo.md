```java
Scanner sc = new Scanner();
//空格和换行敏感
sc.hasNext();         //判断是否有字符
sc.nextInt();         //获取一个整数读到空格或者换行结束
sc.next();       //读取str读到空格或者换行结束

//空格不敏感
sc.hasNextLine();     //是否有换行   /n
sc.nextLine();        //读取一整行到/n结束
```


#### 参考
[练习题](https://www.nowcoder.com/test/27976983/summary#question)