char* x;

void foo() {
    char arr[50];
    x = &arr[13];
}

int main() {

    foo();
    *x = 'c'; // boom

    return 0;
}