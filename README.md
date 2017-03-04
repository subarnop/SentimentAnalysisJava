# SentimentAnalysisJava

Sentiment Analysis is a project for determining sentiment of labeled texts obtained from reviews and microblogging websits.

### Basic Steps Follwed

* Tekenzation of the sentment texts
* Removal of Texts that are not responsible for sentiment determination but used largely such as pronouns, prepostions, etc.
* Selection of most frequent 1000 tokens
* Construction of Histograms for every element
* Traing the model with 2/3 rd data elements
* Construction of averaged histogram for both positive and negative class
* Testing with KNN and nBayes classiifers

### Dataset Used

* https://archive.ics.uci.edu/ml/datasets/Sentiment+Labelled+Sentences#

### Basic Usage

```
$ javac KeyGen.java
$ java KeyGen imdb_labelled.txt 1000
$ javac Main.java
$ java Main imdb_labelled.txt 
```
