#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

const char *splitTrainingFilename[10] = 
{
"./mydatasets/training/my-digit-training-0.txt",
"./mydatasets/training/my-digit-training-1.txt",
"./mydatasets/training/my-digit-training-2.txt",
"./mydatasets/training/my-digit-training-3.txt",
"./mydatasets/training/my-digit-training-4.txt",
"./mydatasets/training/my-digit-training-5.txt",
"./mydatasets/training/my-digit-training-6.txt",
"./mydatasets/training/my-digit-training-7.txt",
"./mydatasets/training/my-digit-training-8.txt",
"./mydatasets/training/my-digit-training-9.txt"
};
const char *splitTestingFilename[10] = 
{
"./mydatasets/testing/my-digit-testing-0.txt",
"./mydatasets/testing/my-digit-testing-1.txt",
"./mydatasets/testing/my-digit-testing-2.txt",
"./mydatasets/testing/my-digit-testing-3.txt",
"./mydatasets/testing/my-digit-testing-4.txt",
"./mydatasets/testing/my-digit-testing-5.txt",
"./mydatasets/testing/my-digit-testing-6.txt",
"./mydatasets/testing/my-digit-testing-7.txt",
"./mydatasets/testing/my-digit-testing-8.txt",
"./mydatasets/testing/my-digit-testing-9.txt"
};
const char *splitPredictFilename[8] = 
{
"./mydatasets/predict/0_0.txt",
"./mydatasets/predict/1_0.txt",
"./mydatasets/predict/2_0.txt",
"./mydatasets/predict/3_0.txt",
"./mydatasets/predict/4_0.txt",
"./mydatasets/predict/5_0.txt",
"./mydatasets/predict/6_0.txt",
"./mydatasets/predict/7_0.txt"

};

FILE *FP[10];

int  kk[10];/*用来统计训练集中每类样本总数：0~9*/
int tkp[10];/*用来统计测试集的样本总数：0~9*/
int  tk[10];/*用来统计分类正确的样本数：0~9*/

int SUM(int *in, int length)
{
    int i=0, sum=0;
    for(i=0;i<length;i++)
    {
        sum += in[i];
    }
    return sum;
}

void InitFILE(const char **filename, const char *state)
/*初始化文件指针：打开文件*/
{
    int i;
    for(i=0;i<10;i++)
    {
        FP[i] = fopen(filename[i], state);
    }
}
void CloseFILE()
/*关闭文件指针：关闭文件*/
{
    int i;
    for(i=0;i<10;i++)
    {
        fclose(FP[i]);
    }
}

void splitDigits(const char *inputFilename, 
                 const char *outputFilenames[],
                 int *count,
                 int nums)
/*将数据分类: 分成十个文件，每个文件一个数字*/
{
    printf("..Split the digits file: %s.\n",inputFilename);
    InitFILE(outputFilenames, "w");
    memset(count, 10*sizeof(int), 0);
    FILE *ifp = fopen(inputFilename,"r");
    
    int col/*列*/, row/*行*/;
    int i=0, label;
    int *digit = malloc(32*32*sizeof(int));
    char ch;
    char enter;
    while(i<nums)//943
    {
        memset(digit,32*32*sizeof(int),0);
        
        for(row=0; row<32;row ++)
        {
            for(col=0;col<32;col ++)
            {
                fscanf(ifp, "%d", &digit[col + row*32]);
            }
        }
        fscanf(ifp, "%d", &label);
        count[label] ++;
        for(row=0; row<32;row ++)
        {
            for(col=0;col<32;col ++)
            {
                fprintf(FP[label], "%d ", digit[col + row*32]);
            }
            fprintf(FP[label], "\n");
        }
        i++;
    }
    fclose(ifp);
    CloseFILE();
    printf("..Done.\n");
}

void split_datasets()
/*分离数据*/
{
    printf("------------split_datasets------------");
    /*训练数据分离和计数*/
    splitDigits("./mydatasets/my-digit-training.txt", //整体混合的digits
                splitTrainingFilename, //分离的文件名
                kk, //每个数字计数器：0~9
                943); //一共有多少个32*32的数字
    /*测试数据分离和计数*/
    splitDigits("./mydatasets/my-digit-testing.txt", //整体混合的digits
                splitTestingFilename, //分离的文件名
                tkp, //每个数字计数器：0~9
                196); //一共有多少个32*32的数字
    int i;
    for(i=0;i<10;i++)
    { 
        printf("kk[%2d] = %3d,  tpk[%2d] = %3d,\n",
                i,kk[i], i, tkp[i]); 
    }
    printf("sum kk = %d, sum tkp = %d\n", 
            SUM(kk, 10), SUM(tkp, 10));
}

float distance(int *v, int *w)
{
    int i, squareSum=0.0;
    int length=1024;//32*32
    for(i=0;i<length;i++)
    {
        squareSum += pow(v[i]-w[i], 2.0);
    }
    return sqrtf(squareSum);
}

void loadMat(int *digit, 
             const char *filename[], 
             const int *count)
{
    InitFILE(filename, "r");

}

void knn_classifiy()
{
    printf("------------knn_classifiy------------");
    
    int hwLabels[943], hwLabelsCount=0;
    int m = SUM(kk,10);
    int *trainingMat = malloc(32*32*m*sizeof(int));
    
    
    
}



int main(int argc, char** argv)
{
    /*第一步：分离数据*/
    split_datasets();
    /*第二步：计算距离（训练）*/
    knn_classifiy();
    /*第三步：识别*/
    
    return 1;
}