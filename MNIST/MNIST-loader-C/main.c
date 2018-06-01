#define USE_MNIST_LOADER
#define MNIST_DOUBLE
#include "mnist.h"
#include <stdio.h>

int main(int argc, char **argv)
{
    mnist_data *mnist;
    unsigned int cnt;
    int ret;
    int i, j;
    FILE *fp = fopen("train-idx3-ubyte.txt","w");

    if (ret = mnist_load("train-images-idx3-ubyte", "train-labels-idx1-ubyte", &mnist, &cnt)) 
    {
        printf("An error occured: %d\n", ret);
    } 
    else 
    {
        printf("image count: %d, %d\n", cnt, sizeof(mnist->data));
        
        if(1)
        for(j=0;j<cnt;j++)
        {
            //printf("\n%d\n",mnist[j].label);
            for(i=0;i<28*28;i++)
            {
                if(0)
                {
                    if(mnist[j].data[i/28][i%28]==0)
                    {
                        printf(" ");
                    }
                    else 
                    {
                        printf("#",mnist[j].data[i/28][i%28]);
                    }
                    if(!(i%28))
                        printf("\n");
                }
                if(1 && j<10)
                {
                    for(i=0;i<28*28;i++)
                    {
                        if(!(i%(28*28)))
                        {
                            fprintf(fp, "\n%d\n", mnist[j].label);
                        }
                        if(!(i%28) && (i%(28*28)))
                        {
                            fprintf(fp, "\n");
                        }
                        fprintf(fp, "%4.2f ", mnist[j].data[i/28][i%28]);
                        
                    }
                }
                else break;
            }
        }
    }
    free(mnist);
    return 0;
}