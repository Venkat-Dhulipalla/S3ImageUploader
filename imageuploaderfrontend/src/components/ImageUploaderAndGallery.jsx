"use client";

import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ImageUploaderAndGallery = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const [imageName, setImageName] = useState("");
  const [uploading, setUploading] = useState(false);
  const [images, setImages] = useState([]);

  const ref = useRef();

  const handleImageChange = (event) => {
    const image = event.target.files[0];
    setSelectedImage(image);
    setImageName(image ? image.name : "");
  };

  const resetHandler = () => {
    ref.current.value = "";
    setSelectedImage(null);
    setImageName("");
  };

  const handleUpload = () => {
    if (!selectedImage) return;

    setUploading(true);

    const formData = new FormData();
    formData.append("file", selectedImage);

    axios
      .post("http://localhost:8080/api/v1/s3", formData)
      .then((response) => {
        toast.success("Image uploaded successfully");
        setSelectedImage(null);
        setImageName("");
        ref.current.value = "";
        fetchImages(); // Fetch images after successful upload
      })
      .catch((error) => {
        console.error("Error uploading image:", error);
        toast.error("Error uploading image");
      })
      .finally(() => {
        setUploading(false);
      });
  };

  const fetchImages = () => {
    axios
      .get("http://localhost:8080/api/v1/s3")
      .then((response) => {
        setImages(response.data);
      })
      .catch((error) => {
        console.error("Error fetching images:", error);
      });
  };

  useEffect(() => {
    fetchImages(); // Fetch images when component mounts
  }, []);

  return (
    <div>
      <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-lg shadow-xl">
        <h1 className="text-2xl font-bold mb-4 text-center text-gray-800">
          Image Uploader and Gallery
        </h1>
        <input
          ref={ref}
          type="file"
          accept="image/*"
          onChange={handleImageChange}
          className="block w-full border border-gray-300 rounded-md p-3 mb-4"
        />
        {imageName && (
          <div className="text-center mb-4">
            <p className="text-gray-600">Selected Image: {imageName}</p>
          </div>
        )}
        {selectedImage && (
          <div className="text-center mb-4">
            <img
              src={URL.createObjectURL(selectedImage)}
              alt="Selected"
              className="max-w-full h-auto rounded-md border border-gray-300"
            />
          </div>
        )}
        <div className="flex justify-between">
          <button
            onClick={handleUpload}
            disabled={!selectedImage || uploading}
            className={`w-2/5 bg-blue-500 text-white py-2 px-4 rounded-md ${
              (!selectedImage || uploading) && "opacity-50 cursor-not-allowed"
            }`}
          >
            {uploading ? "Uploading..." : "Upload"}
          </button>
          <button
            onClick={resetHandler}
            className="w-2/5 bg-red-500 text-white py-2 px-4 rounded-md"
          >
            Clear
          </button>
        </div>
        <ToastContainer />
      </div>
      <h2 className="text-xl font-bold mt-8 mb-4 text-center text-gray-800">
        Image Gallery
      </h2>
      <div className="image-container flex flex-row flex-wrap gap-5">
        {images.length > 0 ? (
          images.map((imageUrl, index) => (
            <img
              key={index}
              src={imageUrl}
              alt={`Image ${index}`}
              style={{ maxWidth: "300px", maxHeight: "300px" }}
              className="rounded-md border border-gray-300"
            />
          ))
        ) : (
          <p className="text-center text-gray-600">No images to display</p>
        )}
      </div>
    </div>
  );
};

export default ImageUploaderAndGallery;
