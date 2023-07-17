import styles from './CreatePost.module.css';

function CreatePost() {
    return (
        <div className={styles.createPost}>
            <div className={styles.createPostLeft}>
                <div className={styles.profilePicture}></div>
            </div>
            <div className={styles.createPostRight}>
                <input className={styles.input}
                    placeholder='Create a post'
                />
                <div className={styles.buttons}>
                    <div className={styles.button}>
                        <i className={`${styles.buttonIcon} fa-solid fa-image`}></i>
                        <p className={styles.buttonText}>Photo</p>
                    </div>
                    <div className={styles.button}>
                        <i className={`${styles.buttonIcon} fa-solid fa-share`}></i>
                        <p className={styles.buttonText}>Post</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CreatePost;
