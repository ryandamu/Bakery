## Bakery Problem

### Algorithm

### Steps to Compile and test

- Clone the repository from GitHub using `git clone https://github.com/ryandamu/Bakery.git`
- `cd RubixBakers`
- It is a Maven project.  Assuming Maven is already installed, execute `mvn clean package` to compile and build a jar file. After successful compilation, a jar file `Bakery-1.0-SNAPSHOT.jar` should exist in `RubixBakers/target` folder.
- Execute `java -cp ./target/Bakery-1.0-SNAPSHOT.jar rubix.Bakers`  to run the program. 
- To Change Input such as changing products/prices, or input, update the following files:
  - `RubixBakers/src/main/resources/prices.dat`
  - `RubixBakers/src/main/resources/order.txt`

### Test cases

| S.No. | Test Case                                                    | Expected Result                                              |
| ----- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1.    | Use the Inputs given the problem statement.                  | Result should be as shown in the problem statement document. |
| 2.    | For any of the products, use more than 3 packs. For example, for `Croissant`, use the following packs:  `3 pack` `4 pack` `5 pack` `7 pack` `9 pack` | The program should work without any errors.                  |
| 3.    | For any product, use Customer order as `1`                   | If there no such pack for a product, Customer should be charged `$0`. |
| 4.    | Use multiple combination of burns with very high customer requirement.  For instance, a customer wants `1 million Croissnts`  `0.5 Billion Blueberry Muffins`  `10 Vegemite Scrolls`. | The program should give correct results.  It should produce results quickly as if the  customer requirement is very small. |

