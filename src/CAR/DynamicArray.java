package CAR;

import java.io.*;
public class DynamicArray {
    private Car[] array;
    private int size;

    public DynamicArray() {
        this.array = new Car[10];
        this.size = 0;
    }

    public DynamicArray(Car[] cars) {
        this.array = new Car[cars.length];
        this.size = cars.length;
        System.arraycopy(cars, 0, this.array, 0, this.size);
    }

    public void add(Car car) {
        if (size == array.length) {
            Car[] newArray = new Car[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size] = car;
        size++;
    }

    public void remove(Car car) {
        int index = indexOf(car);
        if (index != -1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null;
            size--;
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public void readFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String model = data[0];
                int year = Integer.parseInt(data[1]);
                double mileage = Double.parseDouble(data[2]);
                String color = data[3];
                Car car = new Car(model, year, mileage, color);
                add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        mergeSort(0, size - 1);
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Car[] leftArray = new Car[n1];
        Car[] rightArray = new Car[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getYear() <= rightArray[j].getYear()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void removeHighMileageCars(double mileageThreshold) {
        for (int i = 0; i < size; i++) {
            if (array[i].getMileage() > mileageThreshold) {
                remove(array[i]);
                i--; // Decrement i to recheck the current index since the array shifted left
            }
        }
    }

    public void writeToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < size; i++) {
                bw.write(array[i].getModel() + "," + array[i].getYear() + "," + array[i].getMileage() + "," + array[i].getColor());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int indexOf(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }
}
