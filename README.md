# Huffman Compression Tool

## Overview
This project implements a Huffman coding-based compression tool. The tool uses Huffman coding to efficiently compress and decompress files. The encoding process involves building a frequency map of characters, constructing a Huffman tree, and generating prefix codes for each character. The decoding process reconstructs the Huffman tree from metadata and decodes the binary data back into the original text.

## Features
- **File Compression**: Compresses text files using Huffman coding.
- **File Decompression**: Decompresses files that were compressed using this tool.
- **Frequency Map**: Builds a frequency map of characters in the input file.
- **Huffman Tree**: Constructs a Huffman tree based on character frequencies.
- **Prefix Codes**: Generates prefix codes for each character.

## Requirements
- Java 8 or higher
- Maven

## Usage
To use the Huffman Compression Tool, run the `MainProgram` class with the following arguments:

```sh
java MainProgram <filePath> <operation {-e|-d}>
