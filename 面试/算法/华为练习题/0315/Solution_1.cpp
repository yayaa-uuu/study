#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Student {
public:
    int index, height, weight;
    Student(int i = 0, int h = 0, int w = 0): index(i), height(h), weight(w) {
    }
};

vector<Student> handleInput() {
    // 1.处理输入
    int n;
    cin >> n;
    vector<Student> student(n);
    for (int i = 0; i < n; i++) {
        cin >> student[i].height;
        student[i].index = i + 1;
    }
    for (int i = 0; i < n; i++) {
        cin >> student[i].weight;
    }
    return student;
}

vector<Student> func1(const vector<Student> &student) {
    // 2.实现函数
    // 三维排序: height -> weight -> index
    auto ret = student;
    sort(ret.begin(), ret.end(), [](Student &s1, Student &s2) {
        if (s1.height != s2.height) {
            return s1.height < s2.height;
        }
        if (s1.weight != s2.weight) {
            return s1.weight < s2.weight;
        }
        return s1.index < s2.index;
    });
    return ret;
}

void handleOutput(const vector<Student> &student) {
    // 3.处理输出
    for (auto &x : student) {
        cout << x.index << " ";
    }
}

int main() {
    auto student = handleInput();
    auto ret = func1(student);
    handleOutput(ret);
    return 0;
}