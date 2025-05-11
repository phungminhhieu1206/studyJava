package com.hieudev.exception;

/**
 * Bắt exception ném ra lúc runtime
 * Khối try catch/finally: khối bọc một đoạn có thể gây exception, sau try bắt buộc có catch hoặc finally
 * Có nên dùng Exception cho tất cả các khối try?
 * -> Không nên do:
 *  + Exception không bắt lỗi error, mà Throwable mới chứa cả Exception, Error. Tuy nhiên Error thường là lỗi nghiêm trọng của JVM, không nên catch
 *  + Có thể làm mất thông tin khi có thể biết chắc chắn lỗi có thể xảy ra do file, do number, ...
 *  + Chỉ nên dùng Exception ở chỗ tổng quát, log lỗi lớp ngoài cùng.
 *
 */
public class ExceptionHandling {
    public static void main(String[] args) {
        /**
         * Nhiều khối catch để xử lý nhiều ngoại lệ. Quy tắc:
         *  + 1 thời điểm chỉ xảy ra 1 exception và chỉ 1 khối catch được xử lý
         *  + Các khối catch cần được sắp xếp từ cụ thể nhất -> trừu tượng nhất.
         *  Do đó nếu để khối catch (Exception e) lên đầu sẽ gặp lỗi "compile time error" do khi có exception sẽ chỉ đi vào khối này!
         */
        try {
            int a[] = new int[5];
            a[5] = 30 / 0;
        } catch (ArithmeticException e) {
            System.out.println("task1 is completed");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("task 2 completed");
        } catch (Exception e) {
            System.out.println("common task completed");
        }

        System.out.println("rest of the code...");

        /**
         * Khối try lồng nhau:
         *  + Tình huống: khối con có thể gây ra 1 exception, khối to ngoài có thể có một exception khác -> cần khối try lồng nhau
         */
        try {
            try {
                System.out.println("Thuc hien phep chia");
                int b = 39 / 0;
            } catch (ArithmeticException e) {
                System.out.println(e);
            }
            try {
                int a[] = new int[5];
                a[5] = 4;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }

            System.out.println("khoi lenh khac");
        } catch (Exception e) {
            System.out.println("xy ly ngoai le");
        }

        /**
         * Finally:
         *  + Dùng để thực thi các task quan trọng: đóng file, đóng kết nối, đóng luồng
         *  + -> Khối finally chắc chắn được chạy dù có exception hay ko, hoặc gặp return trong try.
         *  Tức nếu không có khối catch xử lý exception thì trước khi kết thúc ctr, JVM sẽ gọi khối finally.
         *
         *  Note: Khối finally sẽ không được thực thi nếu chương trình bị thoát (bằng cách gọi System.exit()
         *  hoặc xảy ra một lỗi không thể tránh khiến chương trình bị chết).
         */
        try {
            int data = 25;
            if (data % 2 != 0) {
                System.out.println(data + " is odd number");
                return;
            }
        } catch (ArithmeticException e) {
            System.out.println(e);
        } finally {
            System.out.println("finally block is always executed");
        }

        /**
         * Throw:
         *  + throw trong try có thể được xử lý trong catch nếu catch tương ứng với loại exception throw ra (hoặc lớp cha của nó)
         *  Nếu không có catch phù hợp sẽ lan truyền ra bên ngoài, và có thể 'crash' nếu không có throws hoặc try..catch phù hợp bên ngoài
         */
    }
}
