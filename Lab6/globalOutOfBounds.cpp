int global_arr[50] = {0};

int main(int argv, char** argc) {
    global_arr[50] = 1; // boom in write
    int res = global_arr[50]; // boom in read

    return res;
}