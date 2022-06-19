int main(int argc, char** argv) {
    char *a = new char[8];
    char *b = new char[8];

    a[35] = 'c';

    delete [] a;
    delete [] b;
    return 0;
}