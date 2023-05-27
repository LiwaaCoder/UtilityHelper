# UtilityHelper Library

The UtilityHelper library is a general-purpose utility library for Android development, providing a collection of helper classes and functions to simplify common tasks. It aims to enhance productivity and reduce code duplication by offering various utility functions and modules.


## Installation

You can easily add this library to your project by adding the following dependency to your build.gradle file:

```groovy
    implementation 'com.github.LiwaaCoder:UtilityHelper:1.3.4'

```

## Usage


To use the Utility Helper library in your Java project, simply create an instance of the Every class and call the relevant functions. 
Here's an example:

java

```groovy
 DateUtility dateUtility = new DateUtility();

    FileUtility fileUtility = new FileUtility();

    JsonUtility jsonUtility = new JsonUtility();

    StringUtility stringUtility = new StringUtility();

    HttpUtility httpUtility = new HttpUtility();

    PixelsUtility pixelsUtility = new PixelsUtility();

    MathUtility mathUtility = new MathUtility();
    
    
    
 // Example usage of StringUtility
String input = "Hello, UtilityHelper!";
boolean isEmpty = StringUtility.isNullOrEmpty(input);
String trimmed = StringUtility.trimWhitespace(input);

// Example usage of FileUtility
File file = new File("path/to/file.txt");
boolean exists = FileUtility.isFileExists(file);
String content = FileUtility.readFileContent(file);

// Example usage of DateUtility
Date now = new Date();
String formatted = DateUtility.formatDate(now, "yyyy-MM-dd HH:mm:ss");
long difference = DateUtility.calculateDateDifference(now, otherDate);


```

## API Reference

Here's a list of the functions provided by the  UtilityHelper library:

```groovy


        is_Existed = fileUtility.fileExists(f); // usage of FileUtility helper

        dateUtility.formatDate(new Date(),f); // // usage of DateUtility helper

        jsonUtility.isValidJson(f); // check if f is a valid json
        jsonUtility.parseJsonArray(f); // from json to array

        stringUtility.capitalizeFirstLetter(f);
        stringUtility.generateRandomString(10);


        httpUtility.sendGetRequest("https://api.example.com/data", new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response.isSuccessful()) {
                    String responseData = response.body();
                    // Handle the response data
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle network failure
            }
        });

        pixelsUtility.dpToPixels(this,1.f);

        mathUtility.isPrime(12);
        mathUtility.factorial(2);

```

## Contributing

If you have any suggestions or find any bugs, please feel free to open an issue or submit a pull request. Contributions are always welcome!
