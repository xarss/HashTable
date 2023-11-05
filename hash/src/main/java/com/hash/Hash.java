package com.hash;

public class Hash {
    private Node[] values;
    private int hashType;
    private int size;

    public Hash(int size, String hashType) {
        switch (hashType) {
            case "module":
                this.hashType = 0;
                break;
            case "multiplication":
                this.hashType = 1;
                break;
            case "folding":
                this.hashType = 2;
                break;
            default:
                this.hashType = 0;
                break;
        }
        this.size = size;
        this.values = new Node[size];
    }

    public String getType() {
        if(this.hashType == 0) {
            return "Module";
        }
        else if (this.hashType == 1) {
            return "Multiplication";
        } else {
            return "Folding";
        }
    }

    private int key(int value) {
        if (this.hashType == 0) {
            // MODULE
            return value % this.size;
        } else if (this.hashType == 1) {
            // MULTIPLICATION
            Double constant = 2.23606;
            double result = value * constant;
            result -= Math.floor(result);
            return (int) (this.size * result);
        } else {
            // FOLDING
            int hashCode = 0;
            String key = Integer.toBinaryString(value);

            for (int i = 0; i < key.length(); i += 2) {
                String chunk = key.substring(i, Math.min(i + 2, key.length()));

                int chunkHash = 0;
                for (char c : chunk.toCharArray()) {
                    chunkHash = chunkHash * 256 + (int) c;
                }

                hashCode += chunkHash;
            }
            hashCode = hashCode % this.size;

            return hashCode;
        }
    }

    public int insert(int value) {
        int key = key(value);
        int collisions = 0;
        
        if (this.values[key] == null) {
            this.values[key] = new Node(value);
        } else {
            Node temp = this.values[key];
            while (temp.getNext() != null) {
                temp = temp.getNext();
                collisions++;
            }
            temp.setNext(new Node(value));
        }
        return collisions;
    }

    public void show() {
        int index = 0;
        if (this.hashType == 0) {
            System.out.println("Module: ");
        } else if (this.hashType == 1) {
            System.out.println("Multiplication: ");
        } else {
            System.out.println("Folding: ");
        }
        for (Node node : this.values) {
            String line = "Key: " + index + " | Values: ";
            if(node == null) {
                System.out.println(line + "Null");
                index++;
                continue;
            }
            Node temp = node;

            line += temp.getValue();
            
            while(temp.getNext() != null) {
                temp  = temp.getNext();
                line += " -> " + temp.getValue();
            }
            System.out.println(line);
            index++;
        }
    }

    public int search(int value) {
        int key = key(value);

        if (this.values[key] == null) {
            return 1;
        }
        if (this.values[key].getValue() == value) {
            return 2;
        }
        int comparisions = 0;
        Node temp = this.values[key];
        while (temp.getNext() != null) {
            comparisions++;
            if(temp.getValue() == value) {return comparisions + 1;}
            comparisions++;
            temp = temp.getNext();
        }
        return comparisions;
    }
}
