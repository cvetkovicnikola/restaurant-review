package classifier;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.Filter;

public class NaiveBayesClassifier extends SentimentClassifier {

	public NaiveBayesClassifier(int option) {
		super(option);
	}

	@Override
	protected void buildFilteredClassifier(Instances trainingData, Filter filter)
			throws Exception {
		System.out.println("Building NaiveBayes classifier...");
		NaiveBayes nbClassifier = new NaiveBayes();
		nbClassifier.setUseSupervisedDiscretization(true);
		
		filteredClassifier = new FilteredClassifier();
		filteredClassifier.setClassifier(nbClassifier);
		filteredClassifier.setFilter(filter);
		filteredClassifier.buildClassifier(trainingData);
		
		System.out.println("Finished building! Evaluating model...");
		Evaluation eval = new Evaluation(trainingData); 
		eval.crossValidateModel(filteredClassifier, trainingData, 10, new Random(1));

		switch (option) {
		case 1:
			saveResults("NaiveBayes_unigrams.txt", getResults(eval));
			break;
		case 2:
			saveResults("NaiveBayes_unigramsBigrams.txt", getResults(eval));
			break;
		case 3:
			saveResults("NaiveBayes_bigrams.txt", getResults(eval));
			break;
		case 4:
			saveResults("NaiveBayes_stopwords.txt", getResults(eval));
			break;
		case 5:
			saveResults("NaiveBayes_combo.txt", getResults(eval));
			break;
		default:
			System.out.println("Wrong options input!");
			break;
		}  
		
		
	}
	
}
