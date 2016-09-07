package test;

import classifier.J48Classifier;
import classifier.NaiveBayesClassifier;
import classifier.SentimentClassifier;
import preparation.CreateDatasets;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Test {
	
	public static void main(String[] args) {
		String filePath = "data/reviews.json";
		String trainingArffFile = "reviewsDataset.arff";
		String testReview = "testDataset.arff";
		
		CreateDatasets.createDatasets(filePath);
		
		//In constructor we should pass one of the following number options:
		//  1. classify using UNIGRAMS
		//  2. classify using UNIGRAMS and BIGRAMS
		//  3. classify using BIGRAMS
		//  4. classify using STOPWORDS removal
		
		NaiveBayesClassifier nbClassifier = new NaiveBayesClassifier(2);
//		J48Classifier jClassifier = new J48Classifier(2);
		
		try {
			nbClassifier.buildClassifier(nbClassifier.prepareTrainingData(trainingArffFile));
//			jClassifier.buildClassifier(jClassifier.prepareTrainingData(trainingArffFile));
			nbClassifier.classifyNewReview(testReview);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
