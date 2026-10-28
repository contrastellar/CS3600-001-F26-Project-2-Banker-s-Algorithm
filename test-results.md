#### Test Results


```bash
javac BankInterface.java BankImpl.java TestBankers.java
java TestBankers inPutFile.txt 10 6 7 8
> 
```
##### Test 1: Initial State
```bash
> *
Available:
[10, 6, 7, 8]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [0, 0, 0, 0]
Customer 1: [0, 0, 0, 0]
Customer 2: [0, 0, 0, 0]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
##### Test 02: Valid safe request
```bash
> RQ 0 3 1 2 1
Approved
```
Checkng state
```bash
> *
Available:
[7, 5, 5, 7]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [3, 1, 2, 1]
Customer 1: [0, 0, 0, 0]
Customer 2: [0, 0, 0, 0]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [3, 3, 5, 2]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
##### Test 03: Request exceeds need
```bash
> RQ 1 5 0 0 0
Denied
```
Checking that nothing has changed so far. 
```bash
> *
Available:
[7, 5, 5, 7]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [3, 1, 2, 1]
Customer 1: [0, 0, 0, 0]
Customer 2: [0, 0, 0, 0]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [3, 3, 5, 2]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
##### Test 04: Insufficient available resources
```bash
> RQ 4 0 6 0 0
Denied
```
Checking that nothing has changed so far. 
```bash
> *
Available:
[7, 5, 5, 7]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [3, 1, 2, 1]
Customer 1: [0, 0, 0, 0]
Customer 2: [0, 0, 0, 0]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [3, 3, 5, 2]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
##### Test 05: Unsafe Request
```bash
> RQ 2 1 2 1 1
Approved
```
Then an unsafe request
```bash
> RQ 4 0 1 0 0
Denied
```
##### Test 06: Release Resources
```bash
> RL 0 1 0 1 0
Released
```
Checking Resources
```bash
> *
Available:
[7, 3, 5, 6]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [0, 0, 0, 0]
Customer 2: [1, 2, 1, 1]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [4, 2, 3, 2]
Customer 2: [1, 3, 2, 2]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
##### Test 07: Invalid release

```bash
> RL 0 9 0 0 0
Release denied: customer does not hold that many resources.
```
Customer 0 only holds 2 of R0
```bash
> RL 1 1 0 0 0
Release denied: customer does not hold that many resources.
```
Customer 1 holds nothing

##### Test 08: Multiple Transactions
Check initial 
```bash
> *
Available:
[7, 3, 5, 6]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [0, 0, 0, 0]
Customer 2: [1, 2, 1, 1]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [4, 2, 3, 2]
Customer 2: [1, 3, 2, 2]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
First Transaction
```bash
> RQ 1 2 1 1 1
Approved
```
Check
```bash
> *
Available:
[5, 2, 4, 5]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [2, 1, 1, 1]
Customer 2: [1, 2, 1, 1]
Customer 3: [0, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [2, 1, 2, 1]
Customer 2: [1, 3, 2, 2]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
Second transaction
```bash
> RQ 3 1 0 0 0
Approved
```
Check
```bash
> *
Available:
[4, 2, 4, 5]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [2, 1, 1, 1]
Customer 2: [1, 2, 1, 1]
Customer 3: [1, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [2, 1, 2, 1]
Customer 2: [1, 3, 2, 2]
Customer 3: [5, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
Third transaction
```bash
> RL 1 1 0 0 0
Released
```
Check
```bash
> *
Available:
[5, 2, 4, 5]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [1, 1, 1, 1]
Customer 2: [1, 2, 1, 1]
Customer 3: [1, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [3, 1, 2, 1]
Customer 2: [1, 3, 2, 2]
Customer 3: [5, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
Final transaction
```bash
> RQ 4 1 0 1 0
Denied
```
Check
```bash
> *
Available:
[5, 2, 4, 5]

Maximum:
Customer 0: [6, 4, 7, 3]
Customer 1: [4, 2, 3, 2]
Customer 2: [2, 5, 3, 3]
Customer 3: [6, 3, 3, 2]
Customer 4: [5, 6, 7, 5]

Allocation:
Customer 0: [2, 1, 1, 1]
Customer 1: [1, 1, 1, 1]
Customer 2: [1, 2, 1, 1]
Customer 3: [1, 0, 0, 0]
Customer 4: [0, 0, 0, 0]

Need:
Customer 0: [4, 3, 6, 2]
Customer 1: [3, 1, 2, 1]
Customer 2: [1, 3, 2, 2]
Customer 3: [5, 3, 3, 2]
Customer 4: [5, 6, 7, 5]
```
