import styles from './CreatePost.module.css';
import profilePicture from '../../Assets/profile.jpg';
import { useState, useEffect } from 'react';
import CreatePostModal from './CreatePostModal';

function CreatePost() {
    const [showCreatePostModal, setShowCreatePostModal] = useState(false);

    useEffect(() => {
        if (showCreatePostModal) document.body.classList.add('preventBgScroll');
        if (!showCreatePostModal) document.body.classList.remove('preventBgScroll');
    }, [showCreatePostModal]);

    return (
        <div className={styles.createPost}>
            <div className={styles.createPostLeft}>
                <img src={profilePicture} className={styles.profilePicture} />
            </div>
            <div className={styles.createPostRight}>
                <button className={styles.inputButton} onClick={() => setShowCreatePostModal(true)}>Create a post</button>
            </div>
            {showCreatePostModal && <CreatePostModal closeModal={() => setShowCreatePostModal(false)}/>}
        </div>
    )
}

export default CreatePost;
