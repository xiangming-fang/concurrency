package concurrent.threadpool.fixedthreadpool.demo;

import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @auther：albert.fang
 * @date：2020/11/28 12:12
 * @description：对demo1的进一步演示
 * 将三个学院下的学生，按照学院分组，每个学院的学生提交给一个线程处理。
 */
public class FixedThreadPoolDemo2 {

    public static void main(String[] args) {
        // 用lambda表达式对集合按照某个字段进行分组，key是学院id，value是分组之后的student集合
        Map<Integer, List<Student>> maps = initValue.students.stream().collect(Collectors.groupingBy(Student::getCollegeId));
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Set<Integer> keys = maps.keySet();
        keys.forEach(v -> {
            pool.submit(()->{
                maps.get(v).forEach(student -> {
                    System.out.printf("线程【%s】正在处理学院【%s】下的学生\n",Thread.currentThread().getName(),student.getCollegeName());
                });
            });
        });
        pool.shutdown();
    }

}
class initValue{
     static List<Student> students = new ArrayList<>();
     static {
         for (int i = 0; i < 10; i++) {
             Student student = new Student(1+i,"fang"+i,15+i,"软件学院",1);
             students.add(student);
         }

         for (int i = 10; i < 20; i++) {
             Student student = new Student(1+i,"albert"+i,15+i,"文学院学院",2);
             students.add(student);
         }

         for (int i = 20; i < 30; i++) {
             Student student = new Student(1+i,"fang"+i,15+i,"法学院",3);
            students.add(student);
         }
     }

}
