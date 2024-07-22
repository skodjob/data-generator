
# Data Generator

## Overview

The Data Generator project provides a flexible and customizable way to generate random data for various use cases, such as testing, development, and simulation. The project leverages the [DataFaker](https://github.com/DiUS/java-faker) library to create realistic and diverse datasets. The current implementation includes generators for payment, aviation, StarGate, and StarWars data.

## Features

- Generate realistic data for various domains
- Easily extendable with new data templates
- Uses DataFaker for rich and varied data generation
- Outputs data in JSON format for easy integration

## Local Build

1. Clone the repository:
   ```bash
   git clone https://github.com/skodjob/data-generator.git
   ```
2. Navigate to the project directory:
   ```bash
   cd data-generator
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage

### Include dependency to your maven test project
```xml
<dependency>
    <groupId>io.skodjob</groupId>
    <artifactId>data-generator</artifactId>
</dependency>
```

### Generate Payment Data

To generate payment data, use the `payment_data` template:

```java
import io.skodjob.datagenerator.DataGenerator;
import io.skodjob.datagenerator.enums.ETemplateType;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator(ETemplateType.PAYMENT_FIAT);
        String paymentDataString = dataGenerator.generateData();
        JsonNode paymentDataJson = dataGenerator.generateJsonData();
        System.out.println(paymentDataString);
    }
}
```

### Generate Flight Data

To generate flight data, use the `flights` template:

```java
import io.skodjob.datagenerator.DataGenerator;
import io.skodjob.datagenerator.enums.ETemplateType;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator(ETemplateType.FLIGHTS);
        String paymentDataString = dataGenerator.generateData();
        JsonNode paymentDataJson = dataGenerator.generateJsonData();
        System.out.println(paymentDataString);
    }
}
```

To use different templates just change the input parameter for `DataGenerator` and use it in the same way.

## Templates

The project currently includes the following templates:
- `payment_data`
- `flights`
- `stargate`
- `starwars`
- `iot_device`
- `payroll`

Each template corresponds to a specific data domain and generates JSON data that can be used for various testing and development purposes.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code follows the existing style and includes tests for new functionality.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or issues, please open an issue on the [GitHub repository](https://github.com/skodjob/data-generator).
