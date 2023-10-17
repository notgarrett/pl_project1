
class TrieNode(char: Char) {
    private var nodes: MutableSet<TrieNode> = mutableSetOf()
    private var char: Char
    init {
        this.char = char
    }

    fun compare(comparable: TrieNode) : Boolean {
        return this.char == comparable.getChar()
    }

    fun getNodes() : MutableSet<TrieNode> {
        return this.nodes
    }

    fun getChar() : Char {
        return this.char
    }

    fun add(str: String) {

    }

    fun addUtil(str: String) {

    }




}

class Trie () {
    private var root: TrieNode = TrieNode('0')

    fun print(t: TrieNode = this.root) {
        println(t.getChar())
        t.getNodes().forEach{print(it)}
    }
    fun find(str: String) : TrieNode? {
        val charArray = str.toCharArray().iterator()
        this.root.getNodes().forEach {
            val foundNode = findUtil(charArray, it)
            foundNode?.let { return foundNode }
        }
        return null
    }

    private fun findUtil(iterator: CharIterator, node: TrieNode) : TrieNode? {
        if (!iterator.hasNext()) return node

        val value = iterator.nextChar()

        if (value != node.getChar()) return null

        node.getNodes().forEach {
            findUtil(iterator, it)?.let { return node }
        }

        return null
    }

    fun add(str: String) {
        val potentialNode = find(str)
        val iterator = str.toCharArray().iterator()
        potentialNode?.let {
            iterator.nextChar()
            addUtil(iterator, it)
        }
        addUtil(iterator, this.root)
    }

    fun addUtil(iterator: CharIterator, node: TrieNode = this.root) {
        var throwAwayNode: TrieNode = node
        iterator.forEach {
            val tempNode = TrieNode(it)
            if (throwAwayNode.getNodes().contains(compare)){

            }
            throwAwayNode.getNodes().add(tempNode)
            throwAwayNode = tempNode
        }
    }



}