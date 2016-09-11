package classifier;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;

public class J48Classifier extends SentimentClassifier {

	public J48Classifier(int option) {
		super(option);
	}

	@Override
	protected void buildFilteredClassifier(Instances trainingData, Filter filter)
			throws Exception {
		System.out.println("Building J48 classifier...");
		J48 classifier = new J48();
		
		filteredClassifier = new FilteredClassifier();
		filteredClassifier.setClassifier(classifier);
		filteredClassifier.setFilter(filter);
		filteredClassifier.buildClassifier(trainingData);
		
		System.out.println("Finished building! Evaluating model...");
		Evaluation eval = new Evaluation(trainingData);
		eval.crossValidateModel(filteredClassifier, trainingData, 10, new Random(1));
		
		switch (option) {
		case 1:
			saveResults("J48_unigrams.txt", getResults(eval));
			break;
		case 2:
			saveResults("J48_unigramsBigrams.txt", getResults(eval));
			break;
		case 3:
			saveResults("J48_bigrams.txt", getResults(eval));
			break;
		case 4:
			saveResults("J48_stopwords.txt", getResults(eval));
			break;
		default:
			System.out.println("Wrong options input!");
			break;
		}
		
	}

}
