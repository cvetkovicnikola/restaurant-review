#1. About the project

The main goal of this project is to build binary classifier which will be used for restaurant reviews classification, based on the exposed sentiment (positive, negative). For classificator creation is used "marked" dataset from Yelp Academic Dataset. 
Java language is used for programming with Weka framework, and for classification, Naive Bayes and J48 classificators are used.
Results are compared in order to find which method is the most accurate.

#2. Dataset

For this project is used Yelp dataset (reviews.json). In order to extract data, parser has been implemented. From original dataset, which has attribute 'starts' - numbrer from 1 to 5 which is related to the review, we extract 2000 reviews in a .arff file. If the review has more then 3 starts (4 or 5) it is classified as positive, and if has less (1 or 2) it is negative. The reviews with 3 stars are ignored. The reviews are saved to reviewsDataset.arff file for future processing.

Every review in dataset has 4 attributes:
- **Review text**
- **Sentiment (positive, negative)**
- **No. votes useful**
- **No. votes cool**

Example of review in dataset:
```
'Excellent food. Superb customer service. I miss the mario machines they used to have, but it\'s still a great place steeped in tradition.', positive, 2, 1
```

#3. Technical realization

This project uses Weka library. Weka is a collection of machine learning algorithms for data mining, that was founded at Waikato University of New Zeland. All these algorithms can be used directly from code by importing weka.jar file or through the graphical interface called Weka Explorer. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project, two Weka classes were used: NaiveBayes and J48 for building classifiers. Each of the classifiers is used with four diferent feature extraction algorithms:
- **Unigrams**
- **Unigrams and bigrams**
- **Bigrams**
- **Stopwords removal**

For building classifiers with one of the mentioned extraction algorithms we used NGramTokenizer with options for max n-gram and min n-gram values.

![Alt text](/images/ngramTokenizer.png?raw=true "NGram Tokenizer for unigrams!")

After that, we used StringToWordVector filter, the most important text mining function in Weka. It converts review text data into set of attributes representing word occurrence (depending on the tokenizer) information from the text contained in the strings.

![Alt text](/images/StringToWordVector.png?raw=true "StringToWordVector filter!")

Most of the tokens (words) we received as an output of the StringToWordFilter
will be useless for Sentiment Review. Thus we make a more precise analysis 
of the tokens by using Attribute Selection in conjunction with 
some kind of quality metric, like Information Gain. Using Ranker algorithm for search means that we will keep only those attributes with Information Gain score over 0, and they will be sorted according to their score as well.	

![Alt text](/images/AttributeSelection.png?raw=true "AttributeSelection filter!")
#4. Results

#5. Conclusion
