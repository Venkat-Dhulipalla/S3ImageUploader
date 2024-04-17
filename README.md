# Seamless Image Upload and Display with Spring Boot, ReactJS, and Amazon S3

Welcome to our innovative project showcasing the seamless integration of Spring Boot, ReactJS, and Amazon S3 for efficient image management. This README provides an overview of the project, installation instructions, and usage guidelines.

## Overview

In this project, we have developed a robust solution for uploading and displaying images, leveraging modern technologies:

- **Spring Boot**: A powerful Java-based framework for building RESTful APIs.
- **ReactJS**: A popular JavaScript library for building user interfaces.
- **Amazon S3**: A scalable cloud storage service for storing and retrieving images.

Our application allows users to effortlessly upload images through a sleek ReactJS frontend. Behind the scenes, a robust Spring Boot REST API efficiently handles image storage in Amazon S3 using AWS Client, ensuring reliability and scalability. Upon successful upload, users receive a secure presigned URL, enabling lightning-fast retrieval and display of their images within the ReactJS interface.


## Features

- Effortless Image Upload: Users can upload images with ease through the intuitive ReactJS frontend.
- Secure Storage in Amazon S3: Images are securely stored in Amazon S3, ensuring reliability and scalability.
- Fast Retrieval: Upon successful upload, users receive a secure presigned URL for lightning-fast retrieval and display of their images.
- Modern Technologies: The project leverages modern technologies such as Spring Boot, ReactJS, and AWS, providing an unparalleled user experience.


## Installation

To run this project locally, follow these steps:

1. Clone this repository to your local machine.
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory.
   ```bash
   cd <project-directory>
   ```

3. Install dependencies for the frontend.
   ```bash
   cd imageuploaderfrontend
   npm install
   ```



4. Configure AWS credentials.
    
    Open the application.properties file located in the src/  main/resources directory of the backend project (ImageUploader).

Add your AWS credentials and S3 bucket details to the file

```bash
# Spring application name
spring.application.name=ImageUploader

# AWS credentials
cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
cloud.aws.region.static=YOUR_AWS_REGION

# S3 bucket details
app.s3.bucket=YOUR_S3_BUCKET_NAME
```
Replace YOUR_ACCESS_KEY, YOUR_SECRET_KEY, YOUR_AWS_REGION, and YOUR_S3_BUCKET_NAME with your actual AWS credentials and S3 bucket details. Make sure to keep your credentials secure and do not expose them publicly.

5. Install dependencies for the backend.
   ```bash
   cd ..
   cd ImageUploader
   mvn clean install
   ```

6. Start the backend server.
   ```bash
   java -jar imageuploader-v1.jar
   ```

7. Start the frontend server.
   ```bash
   cd ..
   cd imageuploaderfrontend
   npm run dev
   ```

8. Access the application at `http://localhost:3000` in your web browser.

## Usage

Once the application is running, you can use the following steps to upload and display images:

1. Navigate to the application homepage.
2. Click on the "Upload Image" button to select an image from your local machine.
3. Once the image is uploaded, it will be displayed on the page.
4. You can click on the uploaded image to view it in full size.



#### Replace `<repository-url>` with the actual URL of your repository and `<project-directory>` with the directory name where you've cloned the repository. Make sure to replace the placeholder values with your actual AWS credentials and S3 bucket details. Let me know if you need further assistance!


## Contributing

Contributions are welcome! Feel free to submit bug reports, feature requests, or pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
