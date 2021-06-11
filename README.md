## <p align="center"> Algorithm-N-queens-demo </p>

<p align="center"> <img src="https://github.com/zukahai/Algorithm-N-queens-demo/blob/master/Blog/1.jpg" alt="Tieude" /> </p>

### Mô Phỏng Thuật Toán N Quân Hậu
Nếu bạn là người chưa nghe qua thuật ngữ "Thuật toán N quân hậu", hoặc chưa biết thuật toán đó hoạt động như thế nào, thì hôm nay trong bài viết này mình sẽ chia sẻ cho các bạn hiểu về thuật toán này bằng một chương trình mô phỏng dễ hiểu

### Thuật toán N quân hậu là gì?
Thuật toán N quân hậu là gì?

Như các bạn đã biết thì một quân hầu trên bàn cờ có thể di chuyển theo hàng ngang, cột dọc và 2 đường chéo.
<p align="center"> <img src="https://github.com/zukahai/Algorithm-N-queens-demo/blob/master/Blog/2.png" alt="blogimage" /> </p>

Bài toán được đặt ra như sau: Cho một bàn cờ có kích thước NxN (N ≥ 1), Bạn có thể đặt đúng N quân hậu lên bàn cờ (mỗi ô chỉ chứa tối đa một quân hậu), hãy đưa ra cách đặt N quân hậu sao cho không có 2 quân hậu nào ăn được nhau, nói cách khác là trên mỗi hàng, một cột, mỗi đường chéo của bàn cờ chỉ chứa tối đa một quân hậu.

Ví dụ với N = 4 thì có 2 cách đặt thỏa mãn như sau:
<p align="center"> <img src="https://github.com/zukahai/Algorithm-N-queens-demo/blob/master/Blog/3.png" alt="blogimage" /> </p>
Thuật toán giải bài toán N quân hậu
Nhận xét bài toán: Chúng ta cần đặt N quân hậu sau cho trên mỗi hàng, một cột, mỗi đường chéo của bàn cờ chỉ chứa tối đa một quân hậu, như vậy trên mỗi hàng sẽ có đúng 1 quân hậu được đặt, ta sẽ đánh số quân hậu đặt trên hàng i là quân thậu thứ i.

Như vậy chúng ta có thể làm như sau:

Xét tất cả các trường hợp đặt quân hậu của thứ nhất (có N trường hợp), với mỗi trường hợp đặt quân hậu thứ nhất, ta xét các cách đặt quân hậu thứ 2, quận hậu thứ 2 cũng cũng có thể đặt ở N ví trị trên hàng thứ 2, nhưng nó phải né tránh sau cho không bị quân hậu thứ nhất ăn được nó,... với quân hậu thứ i nó cũng sẽ có N cách đặt, và nó cũng phải né tránh những ô mà i - 1 quân hậu trước đó có thể ăn được nó. Như vậy chúng ta có thể hình dùng là ta sẽ dùng N vòng for lồng nhau, với mỗi vòng for sẽ tìm chỉ số cột của quân hậu đó, để làm được việc này thì sử dụng đệ quy quay lui là hợp lý.

Cách kiểm tra một ô vuông có nằm trong tầm ngắm của các quân hậu trước đó hay không:
- Sử dụng mảng boolean c để đánh dấu các cột của bàn cờ (c[i] = true nếu trên cột i chưa đặt quân hậu nào)
- Sử dụng màng bool c1 để đánh dấu các đường chéo song song với đường chéo chính của bạn cờ (c[i - j + N -1] = true, nghĩa là đường chéo đi qua ô(i, j) và song song với đường chéo chính chưa được đặt quân hậu nào.
- Sử dụng màng bool c2 để đánh dấu các đường chéo song song với đường chéo phụ của bạn cờ (c[i + j - 2] = true, nghĩa là đường chéo đi qua ô(i, j) và song song với đường chéo phụ chưa được đặt quân hậu nào.

Sau tìm xong vị trí của quân hậu thứ N thì ta lưu output đó lại.
Hàm đệ quy được viết như sau:
```Java
bool check(int i, int j) {
    if (c[j] == false || c1[i - j + N - 1] == false || c2[i + j - 2] ==  false)
        return false;
    return true;
}

void NQueen(int i) {
    for (int j = 1; j <= N; j++)
        if (check(i, j)) {
            x[i] = j;
            c[j] = c1[i - j + N - 1] = c2[i + j - 2] = false;
            if (i == N)
                a.push_back(x);
            else
                NQueen(i + 1);
            c[j] = c1[i - j + N - 1] = c2[i + j - 2] = true;
        }
}
```
Các bạn có thể xem full source code C++ [tại đây]("https://github.com/zukahai/Algorithm-N-queens-demo/blob/master/NQueen.cpp")

### Mô phỏng thuật toán N quân hậu
Để mô phỏng thuật toán này mình đã sử dụng java swing, giao diện chính của phần này sẽ như thế này:
<p align="center"> <img src="https://github.com/zukahai/Algorithm-N-queens-demo/blob/master/Blog/4.png" alt="blogimage" /> </p>

### Video Demo
https://www.youtube.com/watch?v=Veh6GkVWPT0
