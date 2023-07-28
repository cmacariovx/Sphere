import { useState } from 'react';
import styles from './ErrorModal.module.css';

function ErrorModal(props: { message: string; action: any; closeModal: any;}) {
    const [message] = useState(props.message);

    return (
        <div className={styles.errorModalBackdrop}>
            <div className={styles.errorModal} onClick={(e) => e.stopPropagation()}>
                <p className={styles.errorMessage}>{message}</p>
                <button className={styles.errorButton} onClick={(e) => {
                    e.stopPropagation();
                    props.action();
                    props.closeModal();
                }}>Ok</button>
            </div>
        </div>
    )
}

export default ErrorModal;
