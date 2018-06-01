#include <stdio.h>
#include <stdlib.h>

#include "mnist_parse.h"


void load_datasets(void){
	FILE *train_images_fp;
	FILE *test_images_fp;
	FILE *train_labels_fp;
	FILE *test_labels_fp;
	mnist_data *train_data;
	mnist_data *train_label_data;
	mnist_data *test_data;
	mnist_data *test_label_data;

	//Train Images
	train_images_fp = fopen("../datasets/train-images.idx3-ubyte", "r");
	train_data = parse_image_file(train_images_fp);
	fclose(train_images_fp);
	//mnist_print_image(train_data);
	
	//Train Labels
	train_labels_fp = fopen("../datasets/train-labels.idx1-ubyte", "r");
	train_label_data = parse_label_file(train_labels_fp);
	fclose(train_labels_fp);
	//mnist_print_label(train_label_data);
	printf("done\n");
}


int main(void){
	load_datasets();
	return 0;
}

