# Experiments with Scala, Spark, and Akka

## Spark Window Function on stock market data

Experiments with with SPARK `sql.functions`:

- `lead`: is used for return calculation
- `avg`: is used for moving average calculation
- `sum`: is used for cumulative sum calculation

The input data is in long format with 3 columns:

- firm identifier,
- date with format `dd.MM.yyyy`, and
- price.

The input data file has no header.


## Send Data to Kinesis

This small projects includes a word data generator and pushes a random sequence wof words to a defined AWS kinesis node. 

## Bahn Developer API Call

In this small project a endpoint on `https://developer.deutschebahn.com/` is called and the data is fetched.

