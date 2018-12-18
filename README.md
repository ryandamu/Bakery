## Bakery Problem

### Algorithm

The algorithm uses simple `divisor` `remainder` logic to figure out best combination of packs to use.  One of the requirements is **to consider the pack with highest number of buns first to save space/packaging costs**. To achieve this, the pack sizes (read from Input file) are `sorted in descending order`.

Consider the following example - 

- Customer wants `13 buns`
- The packs available are `9` `5` `3` 

First, the algorithm tries to see if the customer's requirement can be satisfied with the best possible combination.  This can be obtained

 - By dividing `13` with `9`.  This leaves a remainder of `4`
 - By dividing `4` with `5` to leave a remainder of `4`
 - by dividing `4` with `3` to leave a remainder of `1`.

In the best case, the remainder of the last division would be **Zero**. 

| customer_count | pack_sizes | packs_required | Remainder |
| -------------- | ---------- | -------------- | --------- |
| 13             | 9          | 1              | 4         |
| 4              | 5          | 0              | 4         |
| 4              | 3          | 1              | 1         |

In this case, since it is not zero,  It tries to see If `packs_required` of very immediate pack [In this case 5], can be reduced by 1. so that its remainder will change which might be divisible by `3`. 

But in this case, the `packs_required` of `5` is already `0`.  So, it goes one level up, and reduces the pack count  of `9` by `1`.  As a result,  the matrix will end up like this:

| customer_count | pack_sizes | packs_required | Remainder |
| -------------- | ---------- | -------------- | --------- |
| 13             | 9          | 0              | 13        |
| 13             | 5          | 2              | 3         |
| 3              | 3          | 1              | 0         |

Since the remainder is zero, the algorithm concludes that this is the best possible combination. 



### Steps to Compile and test

- Clone the repository from GitHub using `git clone https://github.com/ryandamu/Bakery.git`
- `cd RubixBakers`
- It is a Maven project.  Assuming Maven is already installed, execute `mvn clean package` to compile and build a jar file. After successful compilation, a jar file `Bakery-1.0-SNAPSHOT.jar` should exist in `RubixBakers/target` folder.
- Execute `java -cp ./target/Bakery-1.0-SNAPSHOT.jar rubix.Bakers`  to run the program. 
- To Change Input such as changing products/prices, or input, update the following files:
  - `RubixBakers/src/main/resources/prices.dat`
  - `RubixBakers/src/main/resources/order.txt`

With default inputs, result looks like this:

```sh
Prices of each of the packs
CF~3       => $5.95
CF~5       => $9.95
CF~9       => $16.99
MB11~2     => $9.95
MB11~5     => $16.95
MB11~8     => $24.95
VS5~3      => $6.99
VS5~5      => $8.99
--------------------------------------------------------------------------------
Order: CF, Quantity: 13 Pack Sizes: [9 5 3 ]
Pack type: -      CF~5, Required packs: 2     X  9.95, Subtotal:    19.90
Pack type: -      CF~3, Required packs: 1     X  5.95, Subtotal:     5.95
Sub-total:                                                          25.85
--------------------------------------------------------------------------------
Order: MB11, Quantity: 14 Pack Sizes: [8 5 2 ]
Pack type: -    MB11~8, Required packs: 1     X 24.95, Subtotal:    24.95
Pack type: -    MB11~2, Required packs: 3     X  9.95, Subtotal:    29.85
Sub-total:                                                          54.80
--------------------------------------------------------------------------------
Order: VS5, Quantity: 10 Pack Sizes: [5 3 ]
Pack type: -     VS5~5, Required packs: 2     X  8.99, Subtotal:    17.98
Sub-total:                                                          17.98
--------------------------------------------------------------------------------
Bill to be paid:                                                    98.63
```



### Test cases

| S.No. | Test Case                                                    | Expected Result                                              |
| ----- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1.    | Use the Inputs given the problem statement.                  | Result should be as shown in the problem statement document. |
| 2.    | For any of the products, use more than 3 packs. For example, for `Croissant`, use the following packs:  `3 pack` `4 pack` `5 pack` `7 pack` `9 pack` | The program should work without any errors.                  |
| 3.    | For any product, use Customer order as `1`                   | If there no such pack for a product, Customer should be charged `$0`. |
| 4.    | Give Customer requirement as `0`                             | Bill to be charged should be `$0`                            |
| 5.    | Use multiple combination of burns with very high customer requirement.  For instance, a customer wants `1 million Croissnts`  `0.5 Billion Blueberry Muffins`  `10 Vegemite Scrolls`. | The program should give correct results.  It should produce results quickly as if the  customer requirement is very small. |

