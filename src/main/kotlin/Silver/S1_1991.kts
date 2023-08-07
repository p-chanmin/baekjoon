import java.io.*
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    data class TreeNode<T>(
        var data: T,
        var left: TreeNode<T>? = null,
        var right: TreeNode<T>? = null
    )

    class Tree{
        var root: TreeNode<String>? = null

        fun add(d: String, l: String, r: String){
            if(root == null){
                root = TreeNode(d)
                if(l != ".") root!!.left = TreeNode(l)
                if(r != ".") root!!.right = TreeNode(r)
            }
            else searchAdd(root!!, d, l, r)
        }
        fun searchAdd(root: TreeNode<String>, d: String, l: String, r: String){
            if(root.data == d){
                if(l != ".") root.left = TreeNode(l)
                if(r != ".") root.right = TreeNode(r)
            }
            else{
                if(root.left != null) searchAdd(root.left!!, d, l, r)
                if(root.right != null) searchAdd(root.right!!, d, l, r)
            }
        }

        fun preOrder(root: TreeNode<String>){
            bw.write(root.data)
            if(root.left != null) preOrder(root.left!!)
            if(root.right != null) preOrder(root.right!!)
        }
        fun inOrder(root: TreeNode<String>){
            if(root.left != null) inOrder(root.left!!)
            bw.write(root.data)
            if(root.right != null) inOrder(root.right!!)
        }
        fun postOrder(root: TreeNode<String>){
            if(root.left != null) postOrder(root.left!!)
            if(root.right != null) postOrder(root.right!!)
            bw.write(root.data)
        }

    }

    val N = br.readLine().toInt()

    var t: Tree = Tree()

    repeat(N){
        br.readLine().split(" ").let { t.add(it[0], it[1], it[2]) }
    }

    t.preOrder(t.root!!)
    bw.write("\n")
    t.inOrder(t.root!!)
    bw.write("\n")
    t.postOrder(t.root!!)
    bw.write("\n")

    bw.flush()
    bw.close()
}



main()