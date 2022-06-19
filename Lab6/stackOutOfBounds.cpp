int main(int argc, char** argv) {
    int stack_arr[50];

    stack_arr[50] = 1; // boom in write
    int res = stack_arr[argc+50]; // boom in read
    
    return res;
}