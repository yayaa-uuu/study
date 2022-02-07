https://baike.baidu.com/item/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F/7214992

#### 算法原理

插入排序是指在待排序的元素中，假设前面n-1(其中n>=2)个数已经是排好顺序的，现将第n个数插到前面已经排好的序列中，然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序


#### 流程图

```puml
@startuml
start

stop
@enduml
```

#### 算法分析

##### 时间复杂度


##### 算法稳定性

#### 算法描述


``` java
int N = a.length;
for (int i = 0; i < N; i++) {
    for (int j = i; j > 0; j--) {
        if (a[j] < a[j - 1]) {
            int temp = a[j];
            a[j] = a[j - 1];
            a[j - 1] = temp;
        }
    }
}
```