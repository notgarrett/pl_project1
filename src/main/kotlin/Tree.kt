class Node<T>(value: Comparable<T>) {
    private var left: Node<T>? = null
    private var right: Node<T>? = null
    private var value: Comparable<T>

    init {
        this.value = value
    }

    fun setLeft(node: Node<T>?) {
        this.left = node
    }

    fun setRight(node: Node<T>?){
        this.right = node
    }

    fun getRight() : Node<T>? {
        return this.right
    }

    fun getLeft() : Node<T>? {
        return this.left
    }

    fun getValue() : Comparable<T> {
        return this.value
    }

}




class Tree<T>(root: Node<T>) {
    private var root: Node<T>

    init {
        this.root = root
    }

    fun add(value: Comparable<T>, root: Node<T> = this.root) {
        val comparison = compareValues(value, root.getValue())

        if (comparison > 0) {
            if (root.getRight() == null) return root.setRight(Node(value))
            return add(value, root.getRight()!!)
        }
        else if (comparison < 0) {
            if (root.getLeft() == null) return root.setLeft(Node(value))
            return add(value, root.getLeft()!!)
        }

        return

    }

    private fun add(node: Node<T>, root: Node<T>? = this.root) {
        if (root == null) {
            this.root = node
            return
        }

        val comparison = compareValues(node.getValue(), root.getValue())

        if (comparison > 0) {
            if (root.getRight() == null) return root.setRight(node)
            return add(node, root.getRight()!!)
        }
        else if (comparison < 0) {
            if (root.getLeft() == null) return root.setLeft(node)
            return add(node, root.getLeft()!!)
        }

        return
    }

    fun dfSearch(value: Comparable<T>, root: Node<T>? = this.root/*, visited: MutableList<Node<T>?> = mutableListOf()*/) : Node<T>? {
        root ?: return null;
//        if (visited.contains(root)) return null
//        visited.add(root)
        if (compareValues(value, root.getValue()) == 0) return root
        val left = dfSearch(value, root.getLeft())
        if (left != null) return left
        val right = dfSearch(value, root.getRight())
        if (right != null) return right
        return null
    }

    fun bfTraversal(root: Node<T>? = this.root) {
        root?.let {
            val queue: MutableList<Node<T>> = mutableListOf()
            queue.add(root)
            while (queue.size != 0) {
                val node = queue.removeAt(0)
                println(node.getValue())
                node.getRight()?.let { queue.add(it) }
                node.getLeft()?.let {queue.add(it) }
            }
        }
    }

    fun delete(value: Comparable<T>, root: Node<T>? = this.root, parent: Node<T> = this.root) {
        root?.let{
            if (compareValues(value, it.getValue()) == 0) {
                if (parent.getRight() == it) parent.setRight(null)
                else if (parent.getLeft() == it) (parent.setLeft(null))
                addSubtree(it.getLeft())
                addSubtree(it.getRight())
                it.setLeft(null)
                it.setRight(null)
                return
            }
            delete(value, it.getLeft(), it)
            delete(value, it.getRight(), it)
        }
    }

    private fun addSubtree(root: Node<T>?) {
        root?.let {
            val left = it.getLeft()
            val right = it.getRight()
            it.setLeft(null)
            it.setRight(null)
            this.add(it)
            this.addSubtree(left)
            this.addSubtree(right)
        }
    }

     fun getSize (root:Node<T>? = this.root) : Int {
        root ?: return 0;
        return 1 + maxOf(getSize(root.getLeft()), getSize(root.getRight()))
    }

    fun getLeavesCount (root:Node<T>? = this.root ) : Int {
        root?.let {
            if (it.getLeft() == null && it.getRight() == null) return 1;
            return getLeavesCount(it.getLeft()) + getLeavesCount(it.getRight())
        }
        return 0;
    }

//    fun printTree(root:Node<T>? = this.root) {
//        root?.let {
//            println("Main: " + it.getValue())
//            printTree(it.getLeft())
//            printTree(it.getRight())
//        }
//    }

    fun isBalanced(root:Node<T>? = this.root) : Boolean {
        root?.let {
            if (!isBalancedHelper(it)) return false
            return isBalanced(it.getLeft()) && isBalanced(it.getRight())
        }
        return true
    }

    private fun isBalancedHelper(root:Node<T>? = this.root) : Boolean {
            root ?: return true
            return getSize(root.getLeft()) == getSize(root.getRight())
                    || getSize(root.getLeft()) == getSize(root.getRight()) - 1
                    || getSize(root.getLeft()) == getSize(root.getRight()) + 1
    }




}