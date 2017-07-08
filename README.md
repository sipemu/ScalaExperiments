# Experiments with Scala, Spark, and Akka

## Stock Prices (Spark Window Functions)

Experiments with with SPARK `sql.functions`:

- `lead`: is used for return calculation
- `avg`: is used for moving average calculation
- `sum`: is used for cumulative sum calculation

The input data is in long format with 3 columns:

- firm identifier,
- date with format `dd.MM.yyyy`, and
- price.

The file has no header is no header.
