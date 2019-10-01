def fizzbuzz() {
    return [
[
name: 'challenge1', 
expect:'''1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz, 31, 32, Fizz, 34, Buzz, Fizz, 37, 38, Fizz, Buzz, 41, Fizz, 43, 44, FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56, Fizz, 58, 59, FizzBuzz, 61, 62, Fizz, 64, Buzz, Fizz, 67, 68, Fizz, Buzz, 71, Fizz, 73, 74, FizzBuzz, 76, 77, Fizz, 79, Buzz, Fizz, 82, 83, Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz''',
nextDesc:'''
#### Challenge 2
Add a line wrap at every 14 characters.
Sample output:
```
1, 2, Fizz, 4,
 Buzz, Fizz, 7
, 8, Fizz, Buz
...
```
''' 
],
[
name: 'challenge2', 
expect:'''1, 2, Fizz, 4,
 Buzz, Fizz, 7
, 8, Fizz, Buz
z, 11, Fizz, 1
3, 14, FizzBuz
z, 16, 17, Fiz
z, 19, Buzz, F
izz, 22, 23, F
izz, Buzz, 26,
 Fizz, 28, 29,
 FizzBuzz, 31,
 32, Fizz, 34,
 Buzz, Fizz, 3
7, 38, Fizz, B
uzz, 41, Fizz,
 43, 44, FizzB
uzz, 46, 47, F
izz, 49, Buzz,
 Fizz, 52, 53,
 Fizz, Buzz, 5
6, Fizz, 58, 5
9, FizzBuzz, 6
1, 62, Fizz, 6
4, Buzz, Fizz,
 67, 68, Fizz,
 Buzz, 71, Fiz
z, 73, 74, Fiz
zBuzz, 76, 77,
 Fizz, 79, Buz
z, Fizz, 82, 8
3, Fizz, Buzz,
 86, Fizz, 88,
 89, FizzBuzz,
 91, 92, Fizz,
 94, Buzz, Fiz
z, 97, 98, Fiz
z, Buzz''',
nextDesc:'''
#### Challenge 3
Print the first 100 numbers from Fibonacci list with FizzBuzz rule and line breaks.
The Fibonacci list is:
```
    1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...
```
Sample output:
```
1, 1, 2, Fizz,
 Buzz, 8, 13, 
Fizz, 34, Buzz
...
```
''' 
],
[
name: 'challenge3', 
expect:'''1, 1, 2, Fizz,
 Buzz, 8, 13, 
Fizz, 34, Buzz
, 89, Fizz, 23
3, 377, Buzz, 
Fizz, 1597, 25
84, 4181, Fizz
Buzz, 10946, 1
7711, 28657, F
izz, Buzz, 121
393, 196418, F
izz, 514229, B
uzz, 1346269, 
Fizz, 3524578,
 5702887, Buzz
, Fizz, 241578
17, 39088169, 
63245986, Fizz
Buzz, 16558014
1, 267914296, 
433494437, Fiz
z, Buzz, 18363
11903, 2971215
073, Fizz, 777
8742049, Buzz,
 20365011074, 
Fizz, 53316291
173, 862675712
72, Buzz, Fizz
, 365435296162
, 591286729879
, 956722026041
, FizzBuzz, 25
04730781961, 4
052739537881, 
6557470319842,
 Fizz, Buzz, 2
7777890035288,
 4494557021285
3, Fizz, 11766
9030460994, Bu
zz, 3080615211
70129, Fizz, 8
06515533049393
, 130496954492
8657, Buzz, Fi
zz, 5527939700
884757, 894439
4323791464, 14
47233402467622
1, FizzBuzz, 3
78890623731439
06, 6130579072
1611591, 99194
853094755497, 
Fizz, Buzz, 42
01961407274896
73, 6798916376
38612258, Fizz
, 177997941600
4714189, Buzz,
 4660046610375
530309, Fizz, 
12200160415121
876738, 197402
74219868223167
, Buzz, Fizz, 
83621143489848
422977, 135301
85234470674604
9, 21892299583
4555169026, Fi
zzBuzz''',
nextDesc: '''
#### Challenge 4
Accept command line argument `--fibonacci` to use Fibonacci list, otherwise use normal numbers.
At the same time support another arguments to indicate number range.
- `<command> 30` means print from 1 to 30
- `<command> 20 25` means print from 20 to 25 (include)
- `<command> --fibonacci 10 15` means print from 10th number in Fibonacci list to 15th (include)
- no argument means print form 1 to 100
'''
],
[
name: 'challenge4', 
args: '--fibonacci 12 17',
expect:'''Fizz, 233, 377, Buzz, Fizz, 1597''',
nextDesc: '''
#### Well Done
'''
]
]}
