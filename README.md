#1. About the project

The main goal of this project is to build a binary classifier which will be used for restaurant reviews classification, based on the exposed sentiment (positive, negative). For building the classificator we have used marked dataset from ["Yelp Academic Dataset"](https://www.yelp.com/dataset_challenge/dataset). 
Java language is used for programming with Weka framework, and for classification, Naive Bayes and J48 classificators are used.
Results are compared in order to find which method is the most accurate.

#2. Dataset

For this project we have used the ["Yelp Dataset"](https://github.com/cvetkovicnikola/restaurant-review/blob/master/Source%20code/data/reviews.json). The most important attribute in the dataset is the attribute 'stars' that shows the rating of a restoraunt in the specific review (values from 1 to 5 ). This attribute is used for creating the dataset used in this project. All review instances that have attribute 'stars' with values 4 or 5 are marked as positive, instances with value 1 or 2 for this attribute are marked as negative. The reviews with 3 stars are ignored. The reviews are saved to reviewsDataset.arff with the following attributes:
- **Review text**
- **Sentiment (positive, negative)**
- **No. votes useful**
- **No. votes cool**

There were total of 2000 review instances extracted and stored in the ["reviewsDataset.arff"](https://github.com/cvetkovicnikola/restaurant-review/blob/master/Dataset/reviewsDataset.arff).

Example of a review in the dataset is given in the Code Listing 1.
```
'Excellent food. Superb customer service. I miss the mario machines they used to have, but it\'s still a great place steeped in tradition.', positive, 2, 1
```
Code Listing 1 - Example of a review instance from the dataset

#3. Technical realization

This project uses [Weka library](http://www.cs.waikato.ac.nz/ml/weka/). Weka is a collection of machine learning algorithms for data mining, that was founded at the Waikato University of New Zeland. All these algorithms can be used directly from code by importing weka.jar file or through the graphical interface called Weka Explorer. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project, two Weka classes were used: *NaiveBayes* and *J48* for building classifiers. Each of the classifiers is used with four diferent feature extraction algorithms:
- **Unigrams**
- **Unigrams and bigrams**
- **Bigrams**
- **Stopwords removal**

For building classifiers with one of the mentioned extraction algorithms we used NGramTokenizer with options for max n-gram and min n-gram values.

![Alt text](/images/ngramTokenizer.png?raw=true "NGram Tokenizer for unigrams!")

After that, we have used *StringToWordVector* filter, the most important text mining function in Weka. It converts review text data into set of attributes representing word occurrence (depending on the tokenizer) information from the text contained in the strings.

![Alt text](/images/StringToWordVector.png?raw=true "StringToWordVector filter!")

Most of the tokens (words) outputed by the StringToWordFilter are useless for the process of sentiment review. Thus we make a more precise analysis of the tokens by using Attribute Selection in conjunction with some kind of a quality metric, like Information Gain. Using Ranker algorithm for search means that we will keep only those attributes with Information Gain score over 0, and they will be sorted according to that score.	

![Alt text](/images/AttributeSelection.png?raw=true "AttributeSelection filter!")

#4. Results

For each of classification methods results are saved in different file. In order to have more accurate results we used 10 folds cross-validation for model evaluation. The results of all of the 8 variations are shown below.

![Alt text](/images/results.png?raw=true "Classifiers results!")

According to the results, Naive Bayes classifier based on unigrams and bigrams together gave good results. They are similar to the case where the same algorithm was used, but with stopwords removal approach. J48 had slightly better results than Naibe Bayes classifier. Best result was achieved when using unigrams and bigrams approach.

#5. Conclusion

We can also conclude that J48 achieves slightly better results than Naive Bayes algorithm. We can see from the results that for this type of text mining best results are achieved when using unigrams and bigrams extraction algorithm. However, as the stopwords removal alogrithm gives similar results, for some future research these two extraction algorithms should be combined and the more appropriate stopwords list should be compiled.
