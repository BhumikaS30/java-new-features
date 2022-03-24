package com.learn.java.java8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.learn.java.java8.model.Address;
import com.learn.java.java8.model.Customer;
import com.learn.java.java8.model.Employee;
import com.learn.java.java8.model.MobileNumber;
import com.learn.java.java8.model.Order;
import com.learn.java.java8.model.Product;
import com.learn.java.java8.model.Student;
import com.learn.java.java8.model.TempStudent;

import static com.learn.java.java8.model.Department.CSE;

public class PracticeQuestions {

    //  Given a list of integers, print out all the even numbers exist in the list using Stream functions?

    public static void findEvenOrOdd(List<Integer> nums) {
        nums.stream()
            .filter(num -> num % 2 == 0)
            .forEach(System.out::println);
    }

    // Given a list of integers, find out all the numbers starting with 1 using Stream functions?
    public static void findNumsStartingWithOne(List<Integer> nums) {
        nums.stream()
            .filter(num -> num.toString().startsWith("1"))
            .forEach(System.out::println);
    }

    // How to find duplicate elements in a given integers list in java using Stream functions?
    public static void findDuplicates(List<Integer> nums) {
        HashSet<Integer> set = new HashSet<>();
        nums.stream()
            .filter(num -> !set.add(num))
            .forEach(System.out::println);
    }

    // Given the list of integers, find the first element of the list using Stream functions?
    public static void findFirstElement(List<Integer> nums) {
        nums.stream()
            .findFirst()
           .ifPresent(System.out::println);
    }

    //  Given a list of integers, find the total number of elements present in the list using Stream functions?
    public static void findTotalNumOfElements(List<Integer> nums) {
        System.out.println(nums.stream().count());
    }

    // Given a list of integers, find the maximum value element present in it using Stream functions?
    public static void findMax(List<Integer> nums) {
        nums.stream()
            .max(Integer::compare)
            .ifPresent(System.out::println);
    }

    // Given a String, find the first non-repeated character in it using Stream functions?
    public static void findFirstNonRepeatingChar(String str) {
        str.chars()
            .mapToObj(s -> Character.toLowerCase((char) s))
            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1L)
            .map(Map.Entry::getKey)
            .findFirst()
            .ifPresent(System.out::println);
    }

    // Given a String, find the first repeated character in it using Stream functions?
    public static void findFirstRepeatingChar(String str) {
        str.chars()
            .mapToObj(s -> Character.toLowerCase((char) s))
            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1L)
            .map(Map.Entry::getKey)
            .findFirst()
            .ifPresent(System.out::println);
    }

    // Given a list of integers, sort all the values present in it using Stream functions?
    public static void sort(List<Integer> nums) {
        nums.stream()
            .sorted()
            .forEach(System.out::println);
    }

    // Given a list of integers, sort all the values present in it in descending order using Stream functions?
    public static void sortDesc(List<Integer> nums) {
        nums.stream()
            .sorted(Collections.reverseOrder())
            .forEach(System.out::println);
    }

    // Group an array of employee records into a data map organized by job title.
    public static Map<String, List<Employee>> groupEmployees(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getJobTitle));
    }

    // Calculate average salary of all employee in the list
    public static void calculateAverage(List<Employee> employeeList) {
        employeeList.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .ifPresent(System.out::println);
    }

    // Obtain a list of products belongs to category “Books” with price > 100

    public static List<Product> findProducts(List<Product> products) {
        return products.stream()
            .filter(product -> ("Books").equals(product.getCategory()) && product.getPrice() > 100)
            .collect(Collectors.toList());
    }

    // Obtain a list of order with products belong to category “Baby”

    public static List<Order> findOrders(List<Order> orders) {
        return orders.stream()
            .filter(order -> order.getProducts().stream()
                .anyMatch(order1 -> ("Baby").equals(order1.getCategory()))
            ).collect(Collectors.toList());

    }

    // Obtain a list of product with category = “Toys” and then apply 10% discount

    public static void findProductsList(List<Product> products) {
         products.stream()
                       .filter(product -> ("Toys").equalsIgnoreCase(product.getCategory()))
            .forEach(product -> product.setPrice(product.getPrice() * 0.1));
    }

    // Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021

    public static List<Product> findProductsList1(List<Order> orders) {
      return orders.stream()
                   .filter(order -> order.getCustomer().getTier() == 2)
                   .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                   .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                   .flatMap(order -> order.getProducts().stream())
                   .distinct()
                   .collect(Collectors.toList());
    }

    // Get the cheapest products of “Books” category

    public static Optional<Product> findProductsList2(List<Product> products) {
        return products.stream()
                       .filter(product -> ("Books").equalsIgnoreCase(product.getCategory()))
                       .min(Comparator.comparing(Product::getPrice));
    }

    //  Get the 3 most recent placed order

    public static List<Order> find3RecentOrders(List<Order> orders) {
        return orders
            .stream()
            .sorted(Comparator.comparing(Order::getOrderDate).reversed())
            .limit(3)
            .collect(Collectors.toList());
    }

    // Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list

    public static List<Product> findOrdersOnDate(List<Order> orders) {
        return orders
            .stream()
            .filter(order -> LocalDate.of(2021, 3, 15).isEqual(order.getOrderDate()))
            .peek(System.out::println)
            .flatMap(order -> order.getProducts().stream())
            .distinct()
            .collect(Collectors.toList());
    }

    // Calculate total lump sum of prices of all orders placed in Feb 2021

    public static Double calculateTotalSumOfOrders(List<Order> orders) {
       return orders.stream()
              .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
              .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 28)) < 0)
                    .flatMap(order -> order.getProducts().stream())
                    .mapToDouble(Product::getPrice)
                    .sum();
    }

    // Calculate order average payment placed on 14-Mar-2021

    public static void calculateAvgPayment(List<Order> orders) {
         orders.stream()
            .filter(order -> LocalDate.of(2021, 3, 14).isEqual(order.getOrderDate()))
            .flatMap(order -> order.getProducts().stream())
            .mapToDouble(Product::getPrice)
            .average()
            .ifPresent(System.out::println);
    }

    // Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
    public static DoubleSummaryStatistics obtainStatisticFigureForProducts(List<Product> products) {
        return products.stream()
            .filter(product -> ("Books").equalsIgnoreCase(product.getCategory()))
            .mapToDouble(Product::getPrice)
            .summaryStatistics();
    }

    // Obtain a data map with order id and order’s product count

    public static Map<Long, Integer> obtainProductMap(List<Order> orders) {
      return orders.stream()
            .collect(
                Collectors.toMap(
                Order::getId,
                order -> order.getProducts().size()
            ));
    }

    // Produce a data map with order records grouped by customer

    public static Map<Customer, List<Order>> obtainDataMap(List<Order> orders) {
        return orders.stream()
            .collect(Collectors.groupingBy(Order::getCustomer));
    }

    // Produce a data map with order record and product total sum

    public static Map<Order, Double> obtainDataMap2(List<Order> orders) {
        return orders.stream()
                     .collect(Collectors.toMap(
                         Function.identity(),
                         order -> order.getProducts().stream()
                                       .mapToDouble(Product::getPrice)
                                       .sum()
                     ));
    }

    // Obtain a data map with list of product name by category

    public static Map<String, List<String>> obtainProductDataMap(List<Product> products) {
        return products.stream()
                     .collect(Collectors.groupingBy(
                         Product::getCategory,
                         Collectors.mapping(Product::getName, Collectors.toList())
                     ));
    }

    // Get the most expensive product by category

    public static Map<String, Optional<Product>> getMostExpensiveProduct(List<Product> products) {
        return products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparing(Product::getPrice))
            ));
    }

    // Get student with exact match name "jayesh"

    public static List<Student> getStudentsByName(List<Student> students) {
        return students.stream()
            .filter(student -> ("jayesh").equalsIgnoreCase(student.getName()))
            .collect(Collectors.toList());
    }

    // Get student with matching address "1235"

    public static List<Student> getStudentsByAddress(List<Student> students) {
        return students.stream()
                       .filter(student -> ("1235").equalsIgnoreCase(student.getAddress().getZipcode()))
                       .collect(Collectors.toList());
    }

    // Get all student having mobile numbers 3333.

    public static List<Student> getStudentsByMobileNumbers(List<Student> students) {
        return students.stream()
            .filter(student111 -> student111.getMobileNumbers().stream().anyMatch(x -> Objects.equals(x.getNumber(), "3333")))
                       .collect(Collectors.toList());
    }

    // Get all student having mobile number 1233 and 1234

    public static List<Student> getStudentsByMobileNumbers2(List<Student> students) {
        return students.stream()
                       .filter(student111 -> student111.getMobileNumbers().stream()
                                                       .allMatch(x -> Objects.equals(x.getNumber(), "3333") || Objects.equals(x.getNumber(), "1234")))
                       .collect(Collectors.toList());
    }

    // Create a List<Student> from the List<TempStudent>

    public static void getStudents(List<TempStudent> tempStudents) {

    }

    // Convert List<Student> to List<String> of student name

    public static List<String> getStudentNames(List<Student> students) {
       return students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
    }

    // Convert List<students> to String

    public static String getStudentsStrings(List<Student> students) {
        return students.stream()
                       .map(Student::getName)
                       .collect(Collectors.joining(",", "[", "]"));
    }

    // Change the case of List<String>

    public static String changeCase(List<Student> students) {
        return students.stream()
                       .map(Student::getName)
                       .map(String::toUpperCase)
                       .collect(Collectors.joining(",", "[", "]"));
    }

    // Sort List<String>

    public static String sortStrings(List<Student> students) {
        return students.stream()
                       .map(Student::getName)
                       .sorted()
                       .collect(Collectors.joining(",", "[", "]"));
    }

    public static void main(String[] args) {
        /*List<Integer> nums = Arrays.asList(1,2,3,3,2,7,8,8,10);
        sortDesc(nums);
        findFirstRepeatingChar("Java Hungry Blog Alive is Awesome");*/
        /*Employee e1 = new Employee("Bhumika Sharma", "42355", CSE, "Sr. Software Engineer", 1000D);
        Employee e2 = new Employee("Pratik Patil", "42356", CSE, "Sr. Software Engineer", 1000D);
        Employee e3 = new Employee("Yatish Sharma", "42357", CSE, "Software Engineer", 100D);
        Employee e4 = new Employee("Pranit Patil", "42358", CSE, "Software Engineer", 100D);

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        List<Product> productList = new ArrayList<>();
        Set<Product> products = new HashSet<>();
        Set<Product> products1 = new HashSet<>();
        Product product1 = new Product(1L, "product1", "Books", 500D);
        Product product2 = new Product(2L, "product2", "Books", 200D);
        Product product3 = new Product(3L, "product3", "Toys", 300D);
        Product product4 = new Product(4L, "product4", "Toys", 200D);
        Product product5 = new Product(5L, "product3", "Toys", 200D);
        Product product6 = new Product(6L, "product4", "Toys", 200D);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        products1.add(product5);
        products1.add(product6);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        Customer customer = new Customer(1L, "Bhumika", 2);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(1L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 2, 3), "Active", customer, products);
        Order order2 = new Order(2L, LocalDate.of(2021, 2, 2), LocalDate.of(2021, 3, 2), "Active", customer, products);
        Order order3 = new Order(3L, LocalDate.now(), LocalDate.now(), "Active", customer, products);
        Order order4 = new Order(4L, LocalDate.now(), LocalDate.now(), "Active", customer, products1);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);*/

        /*calculateAverage(employees);
        List<Product> res = findProducts(productList);
        res.forEach(product -> System.out.println(product.getName()));

        List<Order> ordersRes = findOrders(orders);
        ordersRes.forEach(order -> System.out.println(order.getId()));

        findProductsList(productList);
        productList.forEach(product -> System.out.println(product.getPrice())); */


        Student student1 = new Student(
            "Jayesh",
            20,
            new Address("1234"),
            Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student student2 = new Student(
            "Khyati",
            20,
            new Address("1235"),
            Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student student3 = new Student(
            "Jason",
            20,
            new Address("1236"),
            Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(student1, student2, student3);

        System.out.println(getStudentsByName(students));

        System.out.println(sortStrings(students));

    }
}
