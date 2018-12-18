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
| 4.    | Use multiple combination of burns with very high customer requirement.  For instance, a customer wants `1 million Croissnts`  `0.5 Billion Blueberry Muffins`  `10 Vegemite Scrolls`. | The program should give correct results.  It should produce results quickly as if the  customer requirement is very small. |

