import styles from './CreatePostModal.module.css';
import profilePicture from '../../Assets/profile.jpg';
import { useRef, useState } from 'react';

function CreatePostModal({ closeModal }: { closeModal: () => void; }) {
    const [showDropdown, setShowDropdown] = useState(false);
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const inputFile = useRef<HTMLInputElement>(null);

    return (
        <div className={styles.createPostModalBackdrop} onClick={closeModal}>
            <div className={styles.createPostModal} onClick={(e) => e.stopPropagation()}>
                <div className={styles.header}>
                    <div className={styles.headerLeft}>
                        <div className={styles.headerLeftLeft}>
                            <img src={profilePicture} className={styles.profilePicture} />
                        </div>
                        <div className={styles.headerLeftRight}>
                            <p className={styles.headerName}>Carlos Macario</p>
                            <p className={styles.headerTitle}>Founder of Sphere</p>
                        </div>
                    </div>
                    <div className={styles.headerRight}>
                        <div className={styles.headerRightDropdownToggle} onClick={() => setShowDropdown(!showDropdown)}>
                            <img src={profilePicture} className={styles.dropdownTogglePic} />
                            <p className={styles.dropdownToggleName}>Choose a community</p>
                        </div>
                        {showDropdown &&
                            <div className={styles.headerRightDropdown}>
                                <div className={styles.headerRightDropdownItem}>
                                    <img src={profilePicture} className={styles.dropdownItemPic} />
                                    <p className={styles.dropdownItemName}>Carlos Macario</p>
                                </div>
                                <div className={styles.headerRightDropdownItem}>
                                    <img src={profilePicture} className={styles.dropdownItemPic} />
                                    <p className={styles.dropdownItemName}>Software Engineering</p>
                                </div>
                            </div>
                        }
                    </div>
                    {showDropdown && <div className={styles.headerRightDropdownBackdrop} onClick={() => setShowDropdown(false)}/>}
                    <i className={`${styles.buttonIconX} fa-solid fa-xmark`} onClick={closeModal}></i>
                </div>
                <div className={styles.modalBody}>
                    <input className={styles.modalBodyTitle} placeholder='Title' maxLength={60}/>
                    <textarea className={styles.modalBodyText} placeholder="What's on your mind?" maxLength={1200}/>
                </div>
                {selectedFile != null &&
                <div className={styles.selectedFileContainer}>
                    <img
                        src={selectedFile ? URL.createObjectURL(selectedFile) : undefined}
                        className={styles.selectedFile}
                    />
                </div>}
                <div className={styles.modalFooter}>
                    <input
                        type="file"
                        id="file"
                        ref={inputFile}
                        accept='image/*'
                        style={{display: 'none'}}
                        onChange={(e: any) => {
                            const file = e.target.files[0];
                            setSelectedFile(file);
                        }}
                    />
                    <div className={styles.button} onClick={() => inputFile.current?.click()}>
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

export default CreatePostModal;
