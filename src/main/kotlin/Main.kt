fun main(args: Array<String>) {
    val root = Node(6)
    val tree = Tree(root)
    tree.add(3)
    tree.add(1)
    tree.add(9)
    tree.add(12)
    tree.add(42)
    tree.add(11)
    println(tree.getSize())
    println(tree.getLeavesCount())
    println(tree.printTree())
}