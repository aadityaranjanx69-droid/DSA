class MyLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    ListNode head;

    public MyLinkedList() {
        head = null;
    }

    public int get(int index) {
        ListNode current = head;

        for (int i = 0; i < index; i++) {
            if (current == null) {
                return -1;
            }
            current = current.next;
        }

        if (current == null) {
            return -1;
        }

        return current.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);

        newNode.next = head;
        head = newNode;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
            return;
        }

        ListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }

        ListNode current = head;

        for (int i = 0; i < index - 1; i++) {
            if (current == null) {
                return;
            }
            current = current.next;
        }

        if (current == null) {
            return;
        }

        ListNode newNode = new ListNode(val);

        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (head == null) {
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        ListNode current = head;

        for (int i = 0; i < index - 1; i++) {
            if (current.next == null) {
                return;
            }
            current = current.next;
        }

        if (current.next == null) {
            return;
        }

        current.next = current.next.next;
    }
}